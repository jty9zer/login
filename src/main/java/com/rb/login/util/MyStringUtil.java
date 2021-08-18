package com.rb.login.util;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;

public class MyStringUtil {

    public static Boolean isEmpty(String str){
        return StrUtil.isEmpty(str);
    }

    /**
     * 获取总页数
     * @param count
     * @param pageSize
     * @return
     */
    public static int totalPages(int count, int pageSize) {
        int totalPages = 0;
        if (count % pageSize == 0) {
            totalPages = (int) Math.floor(count / pageSize);
        } else {
            totalPages = (int) Math.floor(count / pageSize) + 1;
        }
        return totalPages;
    }
}
