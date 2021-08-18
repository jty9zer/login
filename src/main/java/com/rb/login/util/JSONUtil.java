package com.rb.login.util;

import cn.hutool.json.JSONObject;
import org.springframework.stereotype.Component;

public class JSONUtil {

    public static JSONObject toJSONObject(String json){
        return cn.hutool.json.JSONUtil.parseObj(json);
    }

    public static String toJSON(Object obj){
        return cn.hutool.json.JSONUtil.toJsonStr(obj);
    }

    public static <T> T toBean(String json, Class<T> beanClass){
        return cn.hutool.json.JSONUtil.toBean(json,beanClass);
    }
}
