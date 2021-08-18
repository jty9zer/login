package com.rb.login.model.vo;

import lombok.Data;

@Data
public class UserVo {
    private Integer id;
    private String userName;
    private String password;
    private String phone;
}
