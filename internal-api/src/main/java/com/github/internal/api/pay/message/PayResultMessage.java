package com.github.internal.api.pay.message;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author peach
 * @since 2020/12/11 16:26
 */
@Data
public class PayResultMessage {

    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 支付编号
     */
    private Long payId;
    /**
     * 支付方式
     */
    private Integer payType;
    /**
     * 支付时间
     */
    private LocalDateTime payTime;
    /**
     * 支付状态
     */
    private Integer payState;
}