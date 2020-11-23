package com.github.product.task.scheduling;

import com.github.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;
/**
 * mock pv 数据，每秒访问一次
 * @author Zer01ne 
 * @since 2020/11/20 18:51
 */
@Component
@Slf4j
public class MockPVRequestTask {

    @Autowired
    private ProductService productService;

    @Scheduled(cron = "0/1 * * * * ? ")
    public void pv() {
        for (int i = 0; i < 1000; i++) {
            productService.view((long) i);
        }
        log.info(String.format("用户%s浏览了商品！", UUID.randomUUID().toString()));
    }
}
