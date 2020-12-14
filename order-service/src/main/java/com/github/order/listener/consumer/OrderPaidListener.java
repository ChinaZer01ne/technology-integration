package com.github.order.listener.consumer;

import com.alibaba.fastjson.JSONObject;
import com.github.internal.api.order.dto.OrderDTO;
import com.github.internal.api.pay.message.PayResultMessage;
import com.github.order.entity.Order;
import com.github.order.service.OrderService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author Zer01ne
 * @since 2020/12/13 15:46
 */
@RocketMQMessageListener(consumerGroup = "order-service", topic = "OrderPaid")
public class OrderPaidListener implements RocketMQListener {

    @Autowired
    private OrderService orderService;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void onMessage(Object message) {
        PayResultMessage payResultMessage = (PayResultMessage) message;

        System.out.println(message);
        orderService.paid(payResultMessage);

        Order order = orderService.getById(payResultMessage.getOrderId());
        // TODO 扣库存（同步或者异步）
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order, orderDTO);
        Message<String> msg = MessageBuilder.withPayload(JSONObject.toJSONString(orderDTO)).build();
        rocketMQTemplate.sendMessageInTransaction("OrderPaidSuccess",msg, null);
        rocketMQTemplate.sendMessageInTransaction("OrderPaidFailure",msg, null);
    }
}