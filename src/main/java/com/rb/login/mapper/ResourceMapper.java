package com.rb.login.mapper;

import com.rb.login.model.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResourceMapper {

    /**
     * 保存资源信息
     *
     * @param resource
     */
    Integer save(@Param("resource") Resource resource);

    /**
     * 更新资源信息
     *
     * @param resource
     */
    void update(@Param("resource") Resource resource);

    /**
     * 根据资源id查询资源信息
     *
     * @param id
     * @return
     */
    Resource findById(@Param("id") Integer id);

    /**
     * 根据资源名称查询资源信息
     *
     * @param name
     * @return
     */
    List<Resource> findByName(@Param("name") String name);

    /**
     * 查询所有资源信息
     *
     * @return
     */
    List<Resource> findAll();

    /**
     * 分页查询
     *
     * @param start
     * @param count
     * @return
     */
    List<Resource> findByPage(@Param("start") Integer start, @Param("count") Integer count);

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
     * 根据url和method 获取条数
     *
     * @param url
     * @param method
     * @return
     */
    Integer countByUrlAndMethod(String url, String method);
}
