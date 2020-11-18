package com.github.product.task.scheduling;

import com.github.product.entity.Product;
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
public class SpecialPriceProductSyncTask {

    @Scheduled(cron = "")
    public void sync() {
        List<Product> products = mockData();
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