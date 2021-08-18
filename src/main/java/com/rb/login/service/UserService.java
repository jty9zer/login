package com.rb.login.service;

import com.rb.login.model.entity.LoginRecord;
import com.rb.login.model.entity.Resource;
import com.rb.login.model.entity.Role;
import com.rb.login.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public interface UserService extends UserDetailsService {

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;

    public Integer save(User user);

    public long countByUserName(String userName);

    public User findByUserName(String userName);

    public User findById(Integer id);

    public List<User> findByPage();

    public int count();

    public void delete(Integer userId);

    public void grantRole2User(Integer userId, List<Integer> roleIds);

    public List<Role> findRoleByUserId(Integer userId);

    public List<Resource> findResourceByUserId(Integer userId);

    public boolean checkLoginUser(String url, String tokenId, String method);

    public void saveLogin(String userName, String tokenId);

    public void addLogin(String userName);

    public Integer countLogin(String userName, String tokenId);

    public List<LoginRecord> findAllLoginInfo();


}
