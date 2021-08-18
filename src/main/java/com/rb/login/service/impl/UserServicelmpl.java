package com.rb.login.service.impl;

import com.rb.login.exception.BusinessException;
import com.rb.login.mapper.LoginMapper;
import com.rb.login.mapper.UserMapper;
import com.rb.login.mapper.UserRoleMapper;
import com.rb.login.model.entity.*;
import com.rb.login.service.RoleService;
import com.rb.login.service.UserService;
import com.rb.login.util.MyStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServicelmpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private LoginMapper loginMapper;

    /**
     * 只做用户名和密码登录认证。
     *
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("userName:" + userName);
        if (MyStringUtil.isEmpty(userName)) {
            throw new BusinessException("用户名不可为空");
        }
        User user = userMapper.findByUserName(userName);
        if (null == user) {
            throw new BusinessException("用户信息不存在");
        }
        return new org.springframework.security.core.userdetails.User(userName, passwordEncoder.encode(user.getPassword()), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
    }

    @Override
    public Integer save(User user) {
        if (null == user.getId()) {
            //新建
            //验证名称是否重复。
            if (userMapper.countByUserName(user.getUserName()) > 0) {
                throw new BusinessException("用户名称重复");
            }
            userMapper.save(user);
        } else {
            //修改
            User oldUser = userMapper.findById(user.getId());
            if (null == oldUser) {
                throw new BusinessException("用户信息不存在");
            }
            //当修改的名称和之前的名称不一样的时候，需要验证名称是否重复。
            if (!oldUser.getUserName().equals(user.getUserName())) {
                if (userMapper.countByUserName(user.getUserName()) > 0) {
                    throw new BusinessException("用户名称重复");
                }
            }
            userMapper.update(user);
        }
        log.info("id:" + user.getId());
        return user.getId();
    }

    @Override
    public long countByUserName(String userName) {
        return userMapper.countByUserName(userName);
    }

    @Override
    public User findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public List<User> findByPage() {
//        return userMapper.findByPage();
        return userMapper.findAll();
    }

    @Override
    public int count() {
        return userMapper.count();
    }

    @Override
    public void delete(Integer userId) {
        userMapper.delete(userId);
    }

    @Override
    public void grantRole2User(Integer userId, List<Integer> roleIds) {
        //先删除该用户之前的角色
//        userRoleMapper.deleteByUserId(userId);
        userRoleMapper.delete(userId);
        //保存新角色
//        userRoleMapper.save(userId, roleIds);
        for (Integer roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleMapper.save(userRole);
        }
    }

    @Override
    public List<Role> findRoleByUserId(Integer userId) {
        List<Integer> roleIds = userRoleMapper.findByUserId(userId);
        List<Role> roles = new ArrayList<>();
        for (Integer roleId : roleIds) {
            Role role = roleService.findById(roleId);
            roles.add(role);
        }
        return roles;
    }


    @Override
    public List<Resource> findResourceByUserId(Integer userId) {
        List<Integer> roleIds = userRoleMapper.findByUserId(userId);
        List<Resource> resources = new ArrayList<>();
        for (Integer roleId : roleIds) {
            List<Resource> list = roleService.findResourceByRoleId(roleId);
            for (Resource resource : list) {
                if (!resources.contains(resource)) {
                    resources.add(resource);
                }
            }
        }
        return resources;
    }

    @Override
    public boolean checkLoginUser(String url, String tokenId, String method) {
        log.info("请求地址：" + url);
        log.info("请求方式：" + method);
        //根据tokenId获取用户名称
        String userName = loginMapper.findUserNameByTokenId(tokenId);
        if (null == userName || "".equals(userName)) {
            throw new BusinessException("无效的tokenId");
        }
        //如果是超级管理员，不需要验证权限。（管理员帐号为，admin 123456）
        if ("admin".equals(userName)) {
            return true;
        }
        //根据用户名称获取该用户信息
        User user = userMapper.findByUserName(userName);
        if (null == user) {
            throw new BusinessException("用户信息不存在");
        }
        //根据用户信息查询该用户所拥有的资源信息
        List<Resource> resources = findResourceByUserId(user.getId());
        boolean flag = false;
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        //比对url是否在所拥有的资源url中
        for (Resource resource : resources) {
            if (antPathMatcher.match(resource.getUrl(), url) && method.equals(resource.getMethod())) {
                flag = true;
                break;
            }
        }
        if (flag) {
            return true;
        } else {
            throw new BusinessException("无权访问");
        }
    }

    @Override
    public void saveLogin(String userName, String tokenId) {
        loginMapper.saveLogin(userName, tokenId);
    }

    @Override
    public void addLogin(String userName) {
        Integer count = loginMapper.countLoginByUserName(userName);
        if (null == count) {
            loginMapper.firstLogin(userName);
        } else {
            loginMapper.addLogin(userName);
        }
    }

    @Override
    public Integer countLogin(String userName, String tokenId) {
        return loginMapper.countLogin(userName, tokenId);
    }

    @Override
    public List<LoginRecord> findAllLoginInfo() {
        return loginMapper.findAll();
    }
}
