package com.github.pay.handler.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用于接收第三方的支付结果
 * @author Zer01ne
 * @since 2020/11/28 23:50
 */
@Data
public class PayResult {

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
