package com.github.pay.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Zer01ne
 * @since 2020/12/13 16:56
 */
@Data
public class PayLog {

    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 支付时间
     */
    private LocalDateTime payTime;
    /**
     * 支付状态
     */
    private Integer payState;
}
