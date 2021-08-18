package com.rb.login.service.impl;

import com.rb.login.mapper.ResourceMapper;
import com.rb.login.mapper.RoleMapper;
import com.rb.login.mapper.RoleResourceMapper;
import com.rb.login.model.R;
import com.rb.login.model.entity.Resource;
import com.rb.login.model.entity.Role;
import com.rb.login.model.entity.RoleResource;
import com.rb.login.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public int save(Role role) {
        int roleId = roleMapper.save(role);
        return roleId;
    }

    @Override
    public Role findById(Integer id) {
        Role role = roleMapper.findById(id);
        return role;
    }

    @Override
    public List<Resource> findResourceByRoleId(Integer id) {
        List<Integer> resourceIds = roleResourceMapper.findByRoleId(id);
        List<Resource> resources= new ArrayList<>();
        for(Integer resourceId : resourceIds){
            resources.add(resourceMapper.findById(resourceId));
        }
        return resources;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = roleMapper.findAll();
        return roles;
    }

    @Override
    public void delete(Integer id) {
        roleMapper.delete(id);
    }
}
