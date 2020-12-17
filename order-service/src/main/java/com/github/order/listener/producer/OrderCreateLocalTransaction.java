package com.github.order.listener.producer;

import com.alibaba.fastjson.JSONObject;
import com.github.order.entity.Order;
import com.github.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

/**
 * @author Zer01ne
 * @since 2020/12/13 16:46
 */
@Slf4j
@RocketMQTransactionListener
public class OrderCreateLocalTransaction implements RocketMQLocalTransactionListener {

    @Autowired
    private OrderService orderService;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            Order order = JSONObject.parseObject(new String((byte[]) msg.getPayload()),Order.class);
            order.save();
            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e) {
            log.error("订单保存失败！error is {}", e.toString());
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        // TODO 可以单独用日志表记录事务执行状态，单独check日志表

        Order order = JSONObject.parseObject(new String((byte[]) msg.getPayload()),Order.class);
        try {
            Order dbOrder = orderService.getById(order.getOrderId());
            if (dbOrder != null) {
                return RocketMQLocalTransactionState.COMMIT;
            }
            log.error("订单不存在！");
            return RocketMQLocalTransactionState.ROLLBACK;
        }catch (Exception e) {
            log.error("查询数据库超时！");
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }
}
