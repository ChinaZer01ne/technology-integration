package com.github.order.entity;

import com.alibaba.fastjson.JSONObject;
import com.github.common.core.annotation.ddd.Domain;
import com.github.common.core.utils.SpringContextUtil;
import com.github.order.enums.OrderStateEnum;
import com.github.order.mapper.OrderDetailMapper;
import com.github.order.mapper.OrderMapper;
import com.github.order.state.PrepareState;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 订单领域对象
 * @author Zer01ne
 * @since 2020/11/29 1:25
 */
@Slf4j
@Data
@Domain
@Scope("prototype")
public class Order {

    private static final long serialVersionUID = -81667317296658573L;

    private transient OrderMapper orderMapper = SpringContextUtil.getBean(OrderMapper.class);
    private transient OrderDetailMapper orderDetailMapper = SpringContextUtil.getBean(OrderDetailMapper.class);;
    private transient RocketMQTemplate rocketMQTemplate = SpringContextUtil.getBean(RocketMQTemplate.class);;

    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 订单流水号
     */
    private String orderCode;
    /**
     * 订单状态  1:待付款 2:已完成 3:退款中 4:已关闭
     */
    private Integer orderState;
    /**
     * 订单金额
     */
    private Integer amount;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 最终修改时间
     */
    private LocalDateTime modifyTime;
    /**
     * 最终修改时间
     */
    private List<OrderDetail> orderDetailList;

    /**
     * 创建订单
     * @return boolean
     */
    public boolean create() {
        PrepareState state = new PrepareState();
        //state.doAction(this);
        orderState = state.create().getState().getCode();
        Message<String> msg = MessageBuilder.withPayload(JSONObject.toJSONString(this)).build();
        TransactionSendResult sendResult = rocketMQTemplate.sendMessageInTransaction("OrderCreated", msg, null);
        if (Objects.equals(sendResult.getLocalTransactionState(), LocalTransactionState.COMMIT_MESSAGE)) {
            return true;
        }
        return false;
    }

    /**
     * 保存订单信息
     * @return boolean
     */
    public boolean save() {
        log.info("保存订单信息");
        //return orderMapper.insert(this) > 0;
        return true;
    }

    /**
     * 修改订单状态
     * @return boolean
     */
    public boolean paid() {
        log.info("修改订单状态");
        this.setOrderState(OrderStateEnum.UN_SEND.getCode());
        //return orderMapper.update(this) > 0;
        return true;
    }
}
