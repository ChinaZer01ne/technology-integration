package com.github.product.controller;

import com.github.product.constants.ProductConstants;
import com.github.product.service.TopNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * 排行榜
 * @author Zer01ne
 * @since 2020/11/22 16:57
 */
@RestController
@RequestMapping("/topn")
public class TopNController {
    @Autowired
    private TopNService topNService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @GetMapping("day")
    public Set day(){
        return redisTemplate.opsForZSet().reverseRangeWithScores(ProductConstants.DAY_KEY, 0, 30);
    }

    @GetMapping("week")
    public Set week(){
        return redisTemplate.opsForZSet().reverseRangeWithScores(ProductConstants.WEEK_KEY, 0, 30);
    }

    @GetMapping("month")
    public Set month(){
        return redisTemplate.opsForZSet().reverseRangeWithScores(ProductConstants.MONTH_KEY, 0, 30);
    }

    @GetMapping("hour")
    public Set topN(){
        long timeBlock = System.currentTimeMillis() / (1000 * 60 * 60);
        return redisTemplate.opsForZSet().reverseRangeWithScores(ProductConstants.HOUR_KEY + timeBlock, 0, 30);
    }
}
