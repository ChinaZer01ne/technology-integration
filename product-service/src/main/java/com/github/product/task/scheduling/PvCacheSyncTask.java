package com.github.product.task.scheduling;

import com.github.product.constants.ProductConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * 一级缓存PV同步任务
 * @author Zer01ne
 * @since 2020/11/20 23:41
 */
@Component
@Slf4j
public class PvCacheSyncTask {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 一级缓存同步到redis
     * 不同节点最终会把各自的商品pv批量写到redis，这样减少了对redis的写操作
     */
    @Scheduled(cron = "0/2 * * * * ? ")
    public void firstLevelCacheSync() {
        log.info("pv一级缓存消费。。。");
        // 当前时间块
        long timeBlockPerMinutes = System.currentTimeMillis() / (1000 * 60 * 1);
        Iterator<Map.Entry<Long, Map<Long, Long>>> iterator = ProductConstants.PV_MAP.entrySet().iterator();
        while (iterator.hasNext()) {
            // 时间块
            Long key = iterator.next().getKey();
            // 小于当前时间块的就消费
            if (key < timeBlockPerMinutes) {
                // 商品id -> pv
                Map<Long, Long> viewMap = ProductConstants.PV_MAP.get(key);
                // 同步到redis
                redisTemplate.opsForList().leftPush(ProductConstants.PV_LIST_CACHE, viewMap);
                // remove 已消费的key
                ProductConstants.PV_MAP.remove(key);
                log.info("批量同步pv成功：{}",viewMap);
            }
        }
    }

    /**
     * 二级缓存同步数据库
     */
    @Scheduled(cron = "0/3 * * * * ? ")
    public void secondLevelCacheSync() {
        log.info("pv二级缓存消费。。。");
        Map<Long, Long> viewMap = (Map<Long, Long>) redisTemplate.opsForList().rightPop(ProductConstants.PV_LIST_CACHE);
        if (CollectionUtils.isEmpty(viewMap)) {
            return;
        }
        log.info("同步时间块数据到数据库：{}",viewMap);
        // TODO 存入数据库
        for (Map.Entry<Long, Long> viewEntry : viewMap.entrySet()) {
            // 商品id对应的pv值
            String key = String.format(ProductConstants.PRODUCT_PV, viewEntry.getKey());
            redisTemplate.opsForValue().increment(key, viewEntry.getValue());
        }
    }
}
