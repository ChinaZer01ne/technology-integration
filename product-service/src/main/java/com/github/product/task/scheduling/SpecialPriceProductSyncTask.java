package com.github.product.task.scheduling;

import com.github.product.constants.ProductConstants;
import com.github.product.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author peach
 * @since 2020/11/18 16:33
 */
@Component
@Slf4j
public class SpecialPriceProductSyncTask {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 先删除然后重建，会有缓存击穿问题，在某个时间点，流量会全部打到数据库，如何解决？
     *
     * 缓存击穿：针对某一个key的失效，导致大流量打到数据库的问题，主要是热点数据
     * 缓存穿透：针对数据库中没有的数据。用户访问数据，一直查不到缓存，然后流量一直打到数据库，但没查到数据，导致打垮数据库
     * 缓存雪崩：针对大量的key同时过期，导致打垮数据库
     *
     * 1、针对这种定时更新的特定场景，解决方案：可以使用主从轮询的原理解决
     *      步骤一：开辟两块缓存，A和B，定时器在更新缓存的时候，先更新A缓存，然后再更新B缓存，然后记得按这个顺序来
     *      步骤二：用户先查询缓存B，如果缓存B查询不到（例如更新缓存的时候删除了），再查缓存A
     * 2、互斥锁
     * */
    @Scheduled(cron = "* 0/1 * * * ? ")
    public void sync() {
        // 1、获取数据
        List<Product> products = mockData();
        //// 存在缓存击穿的问题
        //rebuildCache(products);
        /**
         * 二级缓存解决缓存击穿问题
         */
        secondLevelCache(products);
        log.info("特价商品数据同步Redis成功！");
    }

    /**
     * 二级缓存解决缓存击穿问题
     * @param products :
     */
    private void secondLevelCache(List<Product> products) {
        // 先删除构建缓存A，再构建缓存B
        redisTemplate.delete(ProductConstants.JHS_A);
        redisTemplate.opsForList().leftPushAll(ProductConstants.JHS_A,products);

        redisTemplate.delete(ProductConstants.JHS_B);
        redisTemplate.opsForList().leftPushAll(ProductConstants.JHS_B,products);
    }

    /**
     * 这存在缓存击穿的问题
     * @param products : 缓存的商品数据
     */
    private void rebuildCache(List<Product> products) {
        // 2、删除redis存在的数据
        redisTemplate.delete(ProductConstants.JHS);
        // 3、保存最新数据
        redisTemplate.opsForList().leftPushAll(ProductConstants.JHS,products);
    }

    /**
     * 模拟商品数据
     */
    private List<Product> mockData() {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Product product = new Product();
            product.setId((long) i);
            product.setName("测试数据" + i);
            product.setPrice(new Random().nextInt(10000));
            product.setViews(new Random().nextLong());
            productList.add(product);
        }
        return productList;
    }
}