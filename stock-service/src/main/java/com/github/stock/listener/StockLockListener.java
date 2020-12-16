package com.github.stock.listener;

import com.github.internal.api.order.dto.OrderDTO;
import com.github.stock.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Zer01ne
 * @since 2020/12/13 16:38
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "stock-lock-consumer", topic = "OrderCreated")
public class StockLockListener implements RocketMQListener<OrderDTO> {

    @Autowired
    private StockService stockService;

    @Override
    public void onMessage(OrderDTO order) {
        log.info("锁定库存");
        stockService.lock(order);
    }
}
