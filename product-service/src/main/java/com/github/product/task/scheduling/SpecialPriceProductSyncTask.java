package com.github.product.task.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author peach
 * @since 2020/11/18 16:33
 */
@Component
public class SpecialPriceProductSyncTask {

    @Scheduled(cron = "")
    public void sync() {

    }

    /**
     * TODO 未来通过RPC接口调用商品中心，获取数据
     * 模拟商品数据
     */
    private void mockData() {

    }
}