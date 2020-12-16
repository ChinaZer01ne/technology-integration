package com.github.order.listener.consumer;

import com.alibaba.fastjson.JSONObject;
import com.github.internal.api.order.dto.OrderDTO;
import com.github.internal.api.pay.message.PayResultMessage;
import com.github.order.entity.Order;
import com.github.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Objects;

/**
 * @author Zer01ne
 * @since 2020/12/13 15:46
 */
@Slf4j
@RocketMQMessageListener(consumerGroup = "order-paid-consumer", topic = "OrderPaid")
public class OrderPaidListener implements RocketMQListener<PayResultMessage> {

    @Autowired
    private OrderService orderService;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void onMessage(PayResultMessage message) {
        PayResultMessage payResultMessage = message;
        log.info("订单已支付：{}", message);
        orderService.paid(payResultMessage);

        Order order = orderService.getById(payResultMessage.getOrderId());
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order, orderDTO);
        Message<String> msg = MessageBuilder.withPayload(JSONObject.toJSONString(orderDTO)).build();
        if (Objects.equals(message.getPayState(), 1)) {
            rocketMQTemplate.sendMessageInTransaction("OrderPaidSuccess",msg, null);
        }else{
            rocketMQTemplate.sendMessageInTransaction("OrderPaidFailure",msg, null);
        }
    }
}
