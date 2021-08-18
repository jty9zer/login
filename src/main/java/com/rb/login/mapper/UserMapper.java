package com.rb.login.mapper;

import com.rb.login.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 保存用户信息
     *
     * @param user
     */
    void save(@Param("user") User user);

    /**
     * 更新用户信息
     *
     * @param user
     */
    void update(@Param("user") User user);

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    User findById(@Param("id") Integer id);

    /**
     * 根据用户名称查询用户信息
     *
     * @param userName
     * @return
     */
    User findByUserName(@Param("userName") String userName);

    /**
     * 查询所有
     *
     * @return
     */
    List<User> findAll();

    /**
     * 分页查询
     *
     * @param start
     * @param count
     * @return
     */
    List<User> findByPage(@Param("start") Integer start, @Param("count") Integer count);

    /**
     * 删除
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
     * 根据用户名称查询条数
     *
     * @param userName
     * @return
     */
    Integer countByUserName(@Param("userName") String userName);
}
