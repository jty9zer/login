package com.rb.login.mapper;

import com.rb.login.model.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    /**
     * 保存
     *
     * @param userRole
     */
    void save(@Param("userRole") UserRole userRole);

    /**
     * 根据用户id查询用户对应的角色列表
     *
     * @param userId
     * @return
     */
    List<Integer> findByUserId(@Param("userId") Integer userId);

    /**
     * 根据用户id删除该用户对应的角色关系
     *
     * @param userId
     */
    void delete(@Param("userId") Integer userId);
}
