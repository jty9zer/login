package com.rb.login.util;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class BeanCopyUtil {

    public static void copy(Object source, Object targetBean) {
        if (null != source) {
            BeanUtil.copyProperties(source, targetBean);
        }
    }

    public static <T> T copy(Object source, Class<T> clazz) {
        if (null != source) {
            T t = BeanUtil.toBean(source, clazz);
            return t;
        }
        return null;
    }

    public static <T> List<T> copyList(Collection<?> sourceList, Class<T> targetClazz) {
        if (sourceList == null) {
            return null;
        }
        List<T> list = new ArrayList<>();
        for (Object source : sourceList) {
            T t = BeanUtil.toBean(source, targetClazz);
            list.add(t);
        }
        return list;
    }
}
