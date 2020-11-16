package com.github.product.config;

import com.github.product.entity.vo.ProductVO;
import com.github.product.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisConfigTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ProductService productService;

    @Test
    public void redisConfigTest() {
        //redisTemplate.opsForValue().set("test", "Hello World!");
        //Assertions.assertEquals(redisTemplate.opsForValue().get("test"), "Hello World!");
    }

    @Test
    public void springCacheConfigTest() {
        productService.get(4L);
        System.out.println(redisTemplate.opsForValue().get("product::4"));
        Assertions.assertNotNull(redisTemplate.opsForValue().get("product::4"));
    }
}