package com.github.order.entity;

import com.github.common.core.annotation.ddd.Domain;
import com.github.order.enums.OrderStateEnum;
import com.github.order.mapper.OrderDetailMapper;
import com.github.order.mapper.OrderMapper;
import com.github.order.state.Context;
import com.github.order.state.PrepareState;
import com.github.order.state.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单领域对象
 * @author Zer01ne
 * @since 2020/11/29 1:25
 */
@Data
@Domain
public class Order {

    private static final long serialVersionUID = -81667317296658573L;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

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
        return orderMapper.insert(this) > 0;
    }
}
