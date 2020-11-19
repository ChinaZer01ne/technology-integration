package com.github.product.service.impl;

import com.github.common.CommonServerResponseEnum;
import com.github.common.ServerResponseEnum;
import com.github.common.exception.Assert;
import com.github.product.entity.bo.RedEnvelope;
import com.github.product.enums.ProductServerResponseEnum;
import com.github.product.service.RedEnvelopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author peach
 * @since 2020/11/19 14:00
 */
@Service
public class RedEnvelopeServiceImpl implements RedEnvelopeService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Long send(Integer amount, Integer size, String desc, Integer type) {
        // 1、生成红包对象
        RedEnvelope redEnvelope = RedEnvelope.generate(type, amount, size, desc);
        // 2、拆分红包
        List<Integer> smallRedEnvelopes = redEnvelope.splitRedEnvelope();
        // 3、保存红包
        // 生成红包id
        Long id = new Random().nextLong();
        redisTemplate.opsForList().leftPushAll(String.format("red:envelope:%d", id), smallRedEnvelopes);
        return id;
    }


    @Override
    public String grab(Long id) {
        Object smallRedEnvelope = redisTemplate.opsForList().leftPop(String.format("red:envelope:%d", id));
        if (smallRedEnvelope == null) {
            return "";
        }
        return String.valueOf(smallRedEnvelope);
    }
}