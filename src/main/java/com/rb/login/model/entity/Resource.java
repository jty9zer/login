package com.rb.login.model.entity;

import lombok.Data;

@Data
public class Resource {
    private Integer id;
    private String code;
    private String name;
    private String url;
    private String method;
}
