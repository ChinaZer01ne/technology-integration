package com.github.product.service.impl;

import com.github.product.constants.ProductConstants;
import com.github.product.utils.BeanUtils;
import com.github.product.entity.vo.ProductVO;
import com.github.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 实现方案：redis自增实现PV统计，缺点：频繁修改redis，如果有一万个商品，每个商品有十万次访问，那么就要修改redis上亿次
     * 思考：1、如何将次数统计同步到数据库？
     *      2、有没有更好的解决方案？zSet，二级缓存
     */
    @Override
    public Boolean view(Long id) {
        // 普通的实现方式，高并发下会有问题
        //return viewNormalImpl(id);
        // 二级缓存的实现方式
        return viewSecondLevelCacheImpl(id);
    }

    /**
     * 二级缓存的方式实现浏览量统计
     * 每次PV操作时，先计算当前时间是哪个时间块，然后存储Map中
     * 时间块：当前时间T/1000*60*60=小时key
     * @param id : 商品id
     * @return java.lang.Boolean
     */
    private Boolean viewSecondLevelCacheImpl(Long id) {
        long timeBlockPerMinutes = System.currentTimeMillis() / (1000 * 60 * 1);
        // Map<时间块，Map<商品id，商品浏览量>>
        Map<Long, Long> viewMap = ProductConstants.PV_MAP.get(timeBlockPerMinutes);
        if (CollectionUtils.isEmpty(viewMap)) {
            viewMap = new ConcurrentHashMap<>(16);
            viewMap.put(id, 1L);
        }else{
            // 合并商品记录
            viewMap.merge(id, 1L, Long::sum);
        }
        return true;
    }

    /**
     * 简单的方式实现浏览量的统计，高并发下会扛不住
     * @param id : 商品id
     * @return java.lang.Boolean
     */
    private Boolean viewNormalImpl(Long id) {
        stringRedisTemplate.opsForValue().increment(String.format(ProductConstants.PRODUCT_PV, id));
        return true;
    }

    @Override
    public ProductVO viewCount(Long id) {
        String increment = stringRedisTemplate.opsForValue().get(String.format(ProductConstants.PRODUCT_PV, id));
        log.info("商品{}访问量为：{}",id,increment);
        ProductVO productVO = new ProductVO();
        productVO.setViews(Long.valueOf(increment));
        productVO.setId(id);
        productVO.setName("机械键盘");
        return productVO;
    }

    @Override
    public Boolean isBlack(Long userId) {
        Boolean member = false;
        try {
            member = redisTemplate.opsForSet().isMember(ProductConstants.COMMENT_BLACKLIST, userId);
        }catch (Exception e) {
            // 走DB查询
            log.error("走DB查询");
        }
        return member;
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
