package com.github.pay.listener.producer;

import com.alibaba.fastjson.JSONObject;
import com.github.pay.entity.PayLog;
import com.github.pay.handler.entity.PayResult;
import com.github.pay.service.PayLogService;
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
public class OrderPaidLocalTransaction implements RocketMQLocalTransactionListener {

    @Autowired
    private PayLogService payLogService;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            PayResult payResult = JSONObject.parseObject(new String((byte[]) msg.getPayload()),PayResult.class);
            PayLog payLog = new PayLog();
            payLog.setOrderId(payResult.getOrderId());
            payLog.setPayTime(payResult.getPayTime());
            payLog.setPayState(payResult.getPayState());
            payLog.setPayType(payResult.getPayType());
            payLogService.insert(payLog);
            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        // TODO 可以单独用日志表记录事务执行状态，单独check日志表

        PayResult payResult = JSONObject.parseObject(new String((byte[]) msg.getPayload()),PayResult.class);
        try {
            PayLog payLog = payLogService.getByPayId(payResult.getPayId());
            if (payLog != null) {
                return RocketMQLocalTransactionState.COMMIT;
            }
            log.error("支付日志不存在！");
            return RocketMQLocalTransactionState.ROLLBACK;
        }catch (Exception e) {
            log.error("查询数据库超时！");
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }
}
