package com.github.product.service.impl;


import com.github.product.constants.ProductConstants;
import com.github.product.entity.Lottery;
import com.github.product.service.LotteryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 抽奖服务
 * @author Zer01ne
 * @since 2020/11/21 10:51
 */
@Slf4j
@Service
public class LotteryServiceImpl implements LotteryService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Lottery repeatable() {
        return (Lottery) redisTemplate.opsForSet().randomMember(ProductConstants.LOTTERY_REPEATABLE);
    }

    @Override
    public Lottery unrepeatable() {
        return (Lottery) redisTemplate.opsForSet().pop(ProductConstants.LOTTERY_REPEATABLE);
    }
}
