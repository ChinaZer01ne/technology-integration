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

    @Override
    public List<ProductVO> getAll() {
        List<ProductVO> productVOList = new ArrayList<>();
        ProductVO productVO = new ProductVO();
        productVO.setId(1L);
        productVO.setName("机械键盘");
        productVOList.add(productVO);

        // 获取商品浏览量
        for (ProductVO product : productVOList) {
            Long views = Long.valueOf(stringRedisTemplate.opsForValue().get(String.format("product:%d", product.getId())));
            product.setViews(views);
        }

        return productVOList;
    }

    @Cacheable(key = "#id")
    @Override
    public ProductVO get(Long id) {
        ProductVO productVO = new ProductVO();
        productVO.setId(1L);
        productVO.setName("机械键盘");

        // 获取商品访问量
        Long views = Long.valueOf(stringRedisTemplate.opsForValue().get(String.format("product:%d", productVO.getId())));
        productVO.setViews(views);

        return productVO;
    }

    /**
     * 实现方案：redis自增实现PV统计，缺点：频繁修改redis，如果有一万个商品，每个商品有十万次访问，那么就要修改redis上亿次
     * 思考：1、如何将次数统计同步到数据库？
     *      2、有没有更好的解决方案？zSet
     */
    @Override
    public ProductVO view(Long id) {
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
