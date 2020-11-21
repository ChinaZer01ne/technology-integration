package com.github.product.task.scheduling;

import com.github.product.constants.ProductConstants;
import com.github.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.LongStream;

/**
 * mock黑名单数据
 * @author Zer01ne 
 * @since 2020/11/20 18:51
 */
@Component
@Slf4j
public class MockBlackListTask {

    @Autowired
    private ProductService productService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Scheduled(cron = "0/1 * * * * ? ")
    public void cacheBlacklist() {
        // 从数据库获取黑名单数据
        List<Long> blacklist = mockData();
        redisTemplate.opsForSet().add(ProductConstants.COMMENT_BLACKLIST, blacklist);
        log.info(String.format("用户%s浏览了商品！", UUID.randomUUID().toString()));
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
