package com.github.product.service.impl;

import com.github.common.Pageable;
import com.github.product.constants.ProductConstants;
import com.github.product.entity.vo.SpecialPriceProductVO;
import com.github.product.service.SpecialPriceService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author peach
 * @since 2020/11/18 16:22
 */
@Slf4j
@Service
public class SpecialPriceServiceImpl implements SpecialPriceService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 这种特价商品场景一般并发量比较大，其中的商品数据一般是预加载到redis中，请求主要是redis来处理。
     */
    @Override
    public Pageable<SpecialPriceProductVO> list(Integer pageNum, Integer pageSize) {
        int start = (pageNum - 1) * pageSize;
        int end = start + pageSize - 1;
        List<SpecialPriceProductVO> productList = null;
        Long size = 0L;
        try {
            size = redisTemplate.opsForList().size(ProductConstants.JHS);
            productList = redisTemplate.opsForList().range(ProductConstants.JHS, start, end);
            if (CollectionUtils.isEmpty(productList)) {
                // TODO 查询数据库
            }
            log.info("特价商品列表：{}", productList);
        } catch (Exception e) {
            // 这里一般是redis宕机或超时
            log.info("查询异常：{}", e.getMessage());
            // TODO 查询数据库

        }

        return Pageable.warp(productList, pageNum, pageSize, size);
    }
}