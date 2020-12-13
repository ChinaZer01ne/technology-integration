package com.github.pay.listener.producer;

import com.github.pay.entity.PayLog;
import com.github.pay.service.PayLogService;
import com.github.pay.service.PayService;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

/**
 * @author Zer01ne
 * @since 2020/12/13 16:46
 */
@RocketMQTransactionListener
public class OrderPaidLocalTransaction implements RocketMQLocalTransactionListener {

    @Autowired
    private PayLogService payLogService;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            PayLog payLog = (PayLog) arg;
            payLogService.save(payLog);
            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        return null;
    }
}
