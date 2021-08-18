package com.rb.login.model.bo;

import lombok.Data;

@Data
public class UserForm {
    private Integer id;
    private String userName;
    private String password;
    private String phone;
}
