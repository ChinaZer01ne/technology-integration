package com.github.order.listener.producer;

import com.alibaba.fastjson.JSONObject;
import com.github.order.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

/**
 * @author Zer01ne
 * @since 2020/12/13 16:46
 */
@Slf4j
@RocketMQTransactionListener
public class OrderCreateLocalTransaction implements RocketMQLocalTransactionListener {

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
        return null;
    }
}
