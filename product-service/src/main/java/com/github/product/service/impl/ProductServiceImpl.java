package com.github.product.service.impl;

import com.github.product.utils.BeanUtils;
import com.github.product.entity.vo.ProductVO;
import com.github.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Zer01ne
 * @since 2020/11/15 19:04
 */
@Slf4j
@CacheConfig(cacheNames = {"product"})
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 实现方案：redis自增实现PV统计，缺点：频繁修改redis，如果有一万个商品，每个商品有十万次访问，那么就要修改redis上亿次
     * 思考：1、如何将次数统计同步到数据库？
     *      2、有没有更好的解决方案？zSet，二级缓存
     */
    @Override
    public ProductVO view(Long id) {
        // 普通的实现方式，高并发下会有问题
        //return viewNormalImpl(id);
        // 二级缓存的实现方式
        return viewSecondLevelCacheImpl(id);
    }

    /**
     * 二级缓存的方式实现浏览量统计
     * @param id : 商品id
     * @return com.github.product.entity.vo.ProductVO
     */
    private ProductVO viewSecondLevelCacheImpl(Long id) {
        return null;
    }

    /**
     * 简单的方式实现浏览量的统计，高并发下会扛不住
     * @param id : 商品id
     * @return com.github.product.entity.vo.ProductVO
     */
    private ProductVO viewNormalImpl(Long id) {
        Long increment = stringRedisTemplate.opsForValue().increment(String.format("product:%d", id));
        log.info("商品{}访问量为：{}",id,increment);
        ProductVO productVO = new ProductVO();
        productVO.setViews(increment);
        productVO.setId(id);
        productVO.setName("机械键盘");
        return productVO;
    }

    @Override
    public Boolean changPrice(Long id, Integer price) {
        ProductVO productVO = new ProductVO();
        productVO.setId(id);
        productVO.setName("机械键盘");
        productVO.setPrice(price);
        Map<String, Object> productMap = BeanUtils.objectToMap(productVO);
        stringRedisTemplate.opsForHash().putAll(String.format("product:%d", id),productMap);
        // TODO chang database
        return true;
    }
}
