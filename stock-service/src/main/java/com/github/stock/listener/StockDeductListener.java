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
@RocketMQMessageListener(consumerGroup = "stock-deduct-consumer", topic = "OrderPaidSuccess")
public class StockDeductListener implements RocketMQListener<OrderDTO> {

    @Autowired
    private StockService stockService;
    @Override
    public void onMessage(OrderDTO order) {
        // TODO 下游消费者要有幂等处理，幂等处理可以用幂等表，需要与业务操作在同一事务

        stockService.deduct(order);
    }
}
