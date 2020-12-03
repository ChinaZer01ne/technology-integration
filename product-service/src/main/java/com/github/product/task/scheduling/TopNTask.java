package com.github.product.task.scheduling;

import com.github.product.constants.ProductConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 定时刷新排行榜数据
 * @author Zer01ne 
 * @since 2020/11/20 18:51
 */
@Component
@Slf4j
public class TopNTask {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Scheduled(cron = "0/5 * * * * ? ")
    public void refresh() {
        // 每5秒模拟刷新排行榜
        refreshPreHour();
        // 合并每天，每周，每月的数据
        merge();
    }

    private void merge() {
        refreshPreDay();
        refreshPreWeek();
        refreshPreMonth();
    }

    private void refreshPreMonth() {
        long timeBlock = System.currentTimeMillis() / (1000 * 60 * 60);
        List<String> otherDays = new ArrayList<>();
        // 求近7天的key
        for (int i = 1; i < 24*30-1; i++) {
            String key = ProductConstants.HOUR_KEY + (timeBlock - i);
            otherDays.add(key);
        }
        // 把当前时间key，并且把后推23个小时，共接近24小时，求并集存入每天的集合中
        redisTemplate.opsForZSet().unionAndStore(ProductConstants.HOUR_KEY + timeBlock, otherDays,ProductConstants.MONTH_KEY);


        log.info("月数据合并完成");
    }

    private void refreshPreWeek() {
        long timeBlock = System.currentTimeMillis() / (1000 * 60 * 60);
        List<String> otherDays = new ArrayList<>();
        // 求近7天的key
        for (int i = 1; i < 24*7-1; i++) {
            String key = ProductConstants.HOUR_KEY + (timeBlock - i);
            otherDays.add(key);
        }
        // 把当前时间key，并且把后推23个小时，共接近24小时，求并集存入每天的集合中
        redisTemplate.opsForZSet().unionAndStore(ProductConstants.HOUR_KEY + timeBlock, otherDays,ProductConstants.WEEK_KEY);


        log.info("周数据合并完成");
    }

    private void refreshPreDay() {
        long timeBlock = System.currentTimeMillis() / (1000 * 60 * 60);
        List<String> otherDays = new ArrayList<>();
        // 求近23个小时的key
        for (int i = 1; i < 23; i++) {
            String key = ProductConstants.HOUR_KEY + (timeBlock - i);
            otherDays.add(key);
        }
        // 把当前时间key，并且把后推23个小时，共接近24小时，求并集存入每天的集合中
        redisTemplate.opsForZSet().unionAndStore(ProductConstants.HOUR_KEY + timeBlock, otherDays,ProductConstants.DAY_KEY);

        for (int i = 0; i < 24; i++) {
            String key = ProductConstants.HOUR_KEY + (timeBlock - i);
            // 设置40天过期
            redisTemplate.expire(key,40, TimeUnit.DAYS);
        }
        log.info("天数据合并完成");
    }

    private void refreshPreHour() {
        long timeBlock = System.currentTimeMillis() / (1000 * 60 * 60);
        Random random = new Random();
        for (int i = 1; i < 26; i++) {
            redisTemplate.opsForZSet().incrementScore(ProductConstants.HOUR_KEY + timeBlock,String.valueOf((char)(96 + i)),random.nextInt(10));
            
        }
    }

    /**
     * 模拟数据库数据
     * @return java.util.List<java.lang.Long>
     */
    private List<Long> mockData() {
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            long userId = new Random().nextLong();
            list.add(userId);
        }
        return list;
    }
}
