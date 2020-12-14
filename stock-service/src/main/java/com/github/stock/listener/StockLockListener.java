package com.github.stock.listener;
import com.github.internal.api.order.dto.OrderDTO;
import com.github.stock.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Zer01ne
 * @since 2020/12/13 16:38
 */
@Slf4j
@RocketMQMessageListener(consumerGroup = "stock-service", topic = "OrderCreated")
public class StockLockListener implements RocketMQListener {

    @Autowired
    private StockService stockService;

    @Override
    public void onMessage(Object message) {
        log.info("锁定库存");
        OrderDTO order = (OrderDTO) message;
        stockService.lock(order);
    }
}
