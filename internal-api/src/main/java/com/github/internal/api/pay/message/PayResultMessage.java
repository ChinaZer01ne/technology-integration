package com.github.internal.api.pay.message;

import lombok.Data;

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
}