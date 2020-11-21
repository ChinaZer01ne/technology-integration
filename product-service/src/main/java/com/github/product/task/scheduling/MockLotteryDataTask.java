package com.github.product.task.scheduling;

import com.github.product.entity.Lottery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * mock抽奖数据
 * @author Zer01ne 
 * @since 2020/11/20 18:51
 */
@Component
@Slf4j
public class MockLotteryDataTask {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Scheduled(cron = "0/1 * * * * ? ")
    public void cacheLotteryData() {
        // 从数据库获取抽奖数据
        List<Lottery> lotteryList = new ArrayList<>();
        redisTemplate.opsForSet().add("lottery:repeatable", lotteryList);
        redisTemplate.opsForSet().add("lottery:unrepeatable", lotteryList);
    }

    /**
     * 模拟数据库数据
     * @return java.util.List<java.lang.Long>
     */
    private List<Lottery> mockData() {
        List<Lottery> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Lottery lottery = new Lottery();
            lottery.setId(1L);
            lottery.setName("Mac book pro M1");
            lottery.setSerialNumber(new Random().nextLong());
            lottery.setProbability(10);
            list.add(lottery);
        }
        for (int i = 0; i < 20; i++) {
            Lottery lottery = new Lottery();
            lottery.setId(2L);
            lottery.setName("机械键盘");
            lottery.setSerialNumber(new Random().nextLong());
            lottery.setProbability(20);
            list.add(lottery);
        }
        for (int i = 0; i < 30; i++) {
            Lottery lottery = new Lottery();
            lottery.setId(4L);
            lottery.setName("鼠标");
            lottery.setSerialNumber(new Random().nextLong());
            lottery.setProbability(30);
            list.add(lottery);
        }
        for (int i = 0; i < 40; i++) {
            Lottery lottery = new Lottery();
            lottery.setId(4L);
            lottery.setName("鼠标垫");
            lottery.setSerialNumber(new Random().nextLong());
            lottery.setProbability(40);
            list.add(lottery);
        }
        return list;
    }
}
