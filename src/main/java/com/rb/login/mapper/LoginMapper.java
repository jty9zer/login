package com.rb.login.mapper;

import com.rb.login.model.entity.LoginRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LoginMapper {
    /**
     * 保存用户和tokenId的关系
     * @param userName
     * @param tokenId
     */
    void saveLogin(@Param("userName")String userName, @Param("tokenId") String tokenId);

    /**
     * 根据用户名称和tokenId查询记录条数
     * @param userName
     * @param tokenId
     * @return
     */
    Integer countLogin(@Param("userName")String userName,@Param("tokenId") String tokenId);

    /**
     * 根据token获取用户名称
     * @param tokenId
     * @return
     */
    String findUserNameByTokenId(String tokenId);

    /**
     * 根据用户名称查询登录次数
     * @param userName
     * @return
     */
    Integer countLoginByUserName(String userName);

    /**
     * 首次登录插入登录记录
     * @param userName
     */
    void firstLogin(String userName);

    /**
     * 登录次数+1
     * @param userName
     */
    void addLogin(String userName);

    /**
     * 查询所有登录记录
     * @return
     */
    List<LoginRecord> findAll();
}
