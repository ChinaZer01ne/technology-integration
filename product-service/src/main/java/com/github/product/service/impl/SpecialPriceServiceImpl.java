package com.github.product.service.impl;

import com.github.common.Pageable;
import com.github.product.constants.ProductConstants;
import com.github.product.entity.vo.SpecialPriceProductVO;
import com.github.product.service.SpecialPriceService;
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
        //return listPage(pageNum, pageSize);
        return secondLevelCacheListPage(pageNum, pageSize);
    }

    /**
     * 二级缓存解决缓存穿透的问题
     * @param pageNum : 当前页
     * @param pageSize ：页大小
     * @return com.github.common.Pageable<com.github.product.entity.vo.SpecialPriceProductVO>
     */
    private Pageable<SpecialPriceProductVO> secondLevelCacheListPage(Integer pageNum, Integer pageSize) {
        int start = (pageNum - 1) * pageSize;
        int end = start + pageSize - 1;
        List<SpecialPriceProductVO> productList = null;
        Long size = 0L;
        try {
            // 先查缓存B
            size = redisTemplate.opsForList().size(ProductConstants.JHS_B);
            productList = redisTemplate.opsForList().range(ProductConstants.JHS_B, start, end);
            if (CollectionUtils.isEmpty(productList)) {
                // 如果B不存在，说明A一定缓存重建完成了
                size = redisTemplate.opsForList().size(ProductConstants.JHS_A);
                productList = redisTemplate.opsForList().range(ProductConstants.JHS_A, start, end);
                if (CollectionUtils.isEmpty(productList)) {
                    // TODO 查询数据库
                }
            }
            log.info("特价商品列表：{}", productList);
        } catch (Exception e) {
            // 这里一般是redis宕机或超时
            log.info("查询异常：{}", e.getMessage());
            // TODO 查询数据库

        }

        return Pageable.warp(productList, pageNum, pageSize, size);
    }

    /**
     * 存在缓存击穿问题
     * @param pageNum : 当前页
     * @param pageSize ：页大小
     * @return com.github.common.Pageable<com.github.product.entity.vo.SpecialPriceProductVO>
     */
    private Pageable<SpecialPriceProductVO> listPage(Integer pageNum, Integer pageSize) {
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