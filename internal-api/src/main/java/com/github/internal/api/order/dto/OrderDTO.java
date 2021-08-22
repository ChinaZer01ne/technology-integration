package com.github.internal.api.order.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author peach
 * @since 2020/12/11 14:28
 */
@Data
public class OrderDTO {
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
     * 订单详情
     */
    private List<OrderDetailDTO> orderDetailList;
}