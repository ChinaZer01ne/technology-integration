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
     * 1、针对这种定时更新的特定场景，解决方案：可以使用主从轮询的原理解决
     *      步骤一：开辟两块缓存，A和B，定时器在更新缓存的时候，先更新B缓存，然后再更新A缓存，然后记得按这个顺序来
     *      步骤二：用户先查询缓存A，如果缓存A查询不到（例如更新缓存的时候删除了），再查缓存B
     * */
    @Scheduled(cron = "* 0/1 * * * ? ")
    public void sync() {
        // 1、获取数据
        List<Product> products = mockData();
        // 2、删除redis存在的数据
        redisTemplate.delete(ProductConstants.JHS);
        // 3、保存最新数据
        redisTemplate.opsForList().leftPushAll(ProductConstants.JHS,products);
        log.info("特价商品数据同步Redis成功！");
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