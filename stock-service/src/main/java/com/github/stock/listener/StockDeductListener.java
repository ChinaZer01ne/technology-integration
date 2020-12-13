package com.github.stock.listener;
import com.github.internal.api.order.dto.OrderDTO;
import com.github.stock.service.StockService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Zer01ne
 * @since 2020/12/13 16:38
 */
@RocketMQMessageListener(consumerGroup = "stock-service", topic = "OrderPaidSuccess")
public class StockDeductListener implements RocketMQListener {

    @Autowired
    private StockService stockService;
    @Override
    public void onMessage(Object message) {
        OrderDTO order = (OrderDTO) message;
        stockService.deduct(order);
    }
}
