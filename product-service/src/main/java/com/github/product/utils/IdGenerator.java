package com.github.product.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author peach
 * @since 2020/11/18 10:30
 */
@Service
public class IdGenerator {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 唯一id生成
     * @return java.lang.Long
     */
    public Long generateId(){
        return stringRedisTemplate.opsForValue().increment("id:generate:cartId");
    }
}