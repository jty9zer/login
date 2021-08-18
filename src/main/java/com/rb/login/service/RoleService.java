package com.rb.login.service;

import com.rb.login.model.entity.Resource;
import com.rb.login.model.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService {
    /**
     * 保存角色信息
     *
     * @param role
     * @return
     */

    int save(@Param("role") Role role);

    /**
     * 根据角色id获取角色详情
     *
     * @param id
     * @return
     */
    Role findById(Integer id);

    /**
     * 根据角色id获取资源列表
     *
     * @param id
     * @return
     */
    List<Resource> findResourceByRoleId(Integer id);

    /**
     * 查询所有的角色信息
     *
     * @return
     */
    List<Role> findAll();

    /**
     * 删除角色
     *
     * @param id
     */
    void delete(Integer id);
}
