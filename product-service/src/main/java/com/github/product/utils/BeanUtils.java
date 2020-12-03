package com.github.product.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author peach
 * @since 2020/11/17 13:04
 */
public class BeanUtils {

    public static Map<String, Object> objectToMap(Object o) {
        Map<String, Object> beanMap = new HashMap<>();
        try {
            for (Field field : o.getClass().getFields()) {
                field.setAccessible(true);
                beanMap.put(field.getName(),field.get(o));
            }
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }

        return beanMap;
    }
}