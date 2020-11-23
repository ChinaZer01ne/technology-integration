package com.github.product.service.impl;

import com.github.product.constants.ProductConstants;
import com.github.product.service.TopNService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;

@Slf4j
@Service
public class TopNServiceImpl implements TopNService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 模拟数据
     */
    public void init(){
        // 小时时间块
        long timeBlock = System.currentTimeMillis() / (1000 * 60 * 60);
        // 初始化30天，每天24个key
        for (int i = 1; i < 24 * 30; i++) {
            // 初始化过去一个月的数据
            String key = ProductConstants.HOUR_KEY + (timeBlock - i);
            doInit(key);
            log.info(key);
        }
    }

    private void doInit(String key) {
        Random random = new Random();
        // 排行数据模拟的英文字母
        for (int i = 1; i < 26; i++) {
            redisTemplate.opsForZSet().add(key, String.valueOf((char)(96 + i)), random.nextInt(10));
        }
    }


}
