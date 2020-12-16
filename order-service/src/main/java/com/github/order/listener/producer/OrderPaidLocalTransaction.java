package com.github.order.listener.producer;

import com.alibaba.fastjson.JSONObject;
import com.github.order.entity.Order;
import com.github.order.enums.OrderStateEnum;
import com.github.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

import java.util.Objects;

/**
 * @author Zer01ne
 * @since 2020/12/13 16:46
 */
@Slf4j
@RocketMQTransactionListener
public class OrderPaidLocalTransaction implements RocketMQLocalTransactionListener {

    @Autowired
    private OrderService orderService;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            Order order = JSONObject.parseObject(new String((byte[]) msg.getPayload()),Order.class);
            order.paid();
            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e) {
            log.error("订单修改状态失败！error is {}", e.toString());
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        Order order = JSONObject.parseObject(new String((byte[]) msg.getPayload()),Order.class);
        try {
            Order dbOrder = orderService.getById(order.getOrderId());
            // 订单存在并且数据库订单修改时间小于等于消息中订单的修改时间（说明订单状态没有回退过）并且订单状态是待发货状态，才认为订单已经支付成功
            if (dbOrder != null && !dbOrder.getModifyTime().isAfter(order.getModifyTime()) && Objects.equals(dbOrder.getOrderState(), OrderStateEnum.UN_SEND.getCode())) {
                return RocketMQLocalTransactionState.COMMIT;
            }
            log.error("订单修改状态失败！");
            return RocketMQLocalTransactionState.ROLLBACK;
        }catch (Exception e) {
            log.error("查询数据库超时！");
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }
}
