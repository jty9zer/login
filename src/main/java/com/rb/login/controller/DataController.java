package com.rb.login.controller;

import com.rb.login.exception.BusinessException;
import com.rb.login.model.bo.ResourceForm;
import com.rb.login.model.bo.RoleForm;
import com.rb.login.model.bo.UserForm;
import com.rb.login.model.entity.Resource;
import com.rb.login.model.entity.Role;
import com.rb.login.model.entity.RoleResource;
import com.rb.login.model.entity.User;
import com.rb.login.model.vo.ResourceVo;
import com.rb.login.model.vo.RoleVo;
import com.rb.login.model.vo.UserVo;
import com.rb.login.service.ResourceService;
import com.rb.login.service.RoleService;
import com.rb.login.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.rb.login.util.BeanCopyUtil.copy;
import static com.rb.login.util.BeanCopyUtil.copyList;


@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;

    final Logger logger = LoggerFactory.getLogger(getClass());

    //****************用户接口****************
    // 新增/修改用户
    @PostMapping("/user")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Integer saveUser(@RequestBody UserForm userForm) {
        User user = copy(userForm, User.class);
        return userService.save(user);
    }

    // 查询用户
    @GetMapping("/user/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public UserVo findUserById(@PathVariable("id") Integer id) {
        User user = userService.findById(id);
        if (null == user) {
            throw new BusinessException("用户不存在");
        }
        logger.info("id:" + user.getId());
        logger.info("userName:" + user.getUserName());
        logger.info("password:" + user.getPassword());
        logger.info("phone:" + user.getPhone());
        UserVo vo = copy(user, UserVo.class);
        return vo;
    }

    // 查询所有用户
    @GetMapping("/user")
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserVo> findAllUser() {
        List<User> users = userService.findByPage();
        List<UserVo> vos = copyList(users, UserVo.class);
        return vos;
    }

    // 删除用户
    @DeleteMapping("/user/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Boolean deleteUser(@PathVariable("id") Integer id) {
        userService.delete(id);
        return true;
    }


    //****************角色接口****************
    // 新增/修改角色
    @PostMapping("/role")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Integer saveRole(@RequestBody RoleForm roleForm) {
        Role role = copy(roleForm, Role.class);
        return roleService.save(role);
    }

    // 查询角色
    @GetMapping("/role/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public RoleVo findRoleById(@PathVariable("id") Integer id) {
        Role role = roleService.findById(id);
        if (null == role) {
            throw new BusinessException("角色不存在");
        }
        logger.info("id:" + role.getId());
        logger.info("name:" + role.getName());
        logger.info("code:" + role.getCode());
        RoleVo vo = copy(role, RoleVo.class);
        return vo;
    }

    // 查询所有角色
    @GetMapping("/role")
    @ResponseStatus(value = HttpStatus.OK)
    public List<RoleVo> findAllRole() {
        List<Role> roles = roleService.findAll();
        List<RoleVo> vos = copyList(roles, RoleVo.class);
        return vos;
    }

    // 删除角色
    @DeleteMapping("/role/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Boolean deleteRole(@PathVariable("id") Integer id) {
        roleService.delete(id);
        return true;
    }


    //****************资源接口****************
    // 新增/修改资源信息
    @PostMapping("/resource")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Integer saveResource(@RequestBody ResourceForm resourceForm) {
        Resource resource = copy(resourceForm, Resource.class);
        return resourceService.save(resource);
    }

    // 查询资源信息
    @GetMapping("/resource/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResourceVo findResourceById(@PathVariable("id") Integer id) {
        Resource resource = resourceService.findById(id);
        if (null == resource) {
            throw new BusinessException("资源信息不存在");
        }
        logger.info("resourceName:" + resource.getName());
        logger.info("url:" + resource.getUrl());
        logger.info("method:" + resource.getMethod());
        ResourceVo vo = copy(resource, ResourceVo.class);
        return vo;
    }

    // 查询所有资源信息
    @GetMapping("/resource")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ResourceVo> findAllResources() {
        List<Resource> resources = resourceService.findAll();
        List<ResourceVo> vos = copyList(resources, ResourceVo.class);
        return vos;
    }

    // 删除资源信息
    @DeleteMapping("/resource/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Boolean deleteResource(@PathVariable("id") Integer id) {
        resourceService.delete(id);
        return true;
    }

}
