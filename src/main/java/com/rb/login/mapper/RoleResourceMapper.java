package com.rb.login.mapper;

import com.rb.login.model.entity.RoleResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleResourceMapper {
    /**
     * 保存
     *
     * @param roleResource
     */
    void save(@Param("roleResource") RoleResource roleResource);

    /**
     * 根据角色id查询角色对应的资源列表
     *
     * @param roleId
     * @return
     */
    List<Integer> findByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据角色id删除该角色对应的资源授权
     *
     * @param roleId
     */
    void delete(@Param("roleId") Integer roleId);
}
