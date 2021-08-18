package com.rb.login.mapper;

import com.rb.login.model.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    /**
     * 保存角色信息
     *
     * @param role
     */
    Integer save(@Param("role") Role role);

    /**
     * 更新角色信息
     *
     * @param role
     */
    void update(@Param("role") Role role);

    /**
     * 根据角色id查询角色详情
     *
     * @param id
     * @return
     */
    Role findById(@Param("id") Integer id);

    /**
     * 根据名称查询角色详情
     *
     * @param name
     * @return
     */
    List<Role> findByName(@Param("name") String name);

    /**
     * 查询所有
     *
     * @return
     */
    List<Role> findAll();

    /**
     * 分页查询
     *
     * @param start
     * @param count
     * @return
     */
    List<Role> findByPage(@Param("start") Integer start, @Param("count") Integer count);

    /**
     * 删除角色
     *
     * @param id
     */
    void delete(@Param("id") Integer id);

    /**
     * 查询记录数
     *
     * @return
     */
    Integer count();

    /**
     * 根据名称查询条数
     *
     * @param name
     * @return
     */
    Integer countByName(@Param("name") String name);
}
