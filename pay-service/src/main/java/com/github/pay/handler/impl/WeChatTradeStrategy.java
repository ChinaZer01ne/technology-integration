package com.github.pay.handler.impl;

import com.github.pay.annotation.PayStrategy;
import com.github.pay.enums.PaymentMethodEnum;
import com.github.pay.handler.entity.PayParam;
import com.github.pay.handler.entity.PayResult;
import com.github.pay.handler.entity.RefundParam;
import com.github.pay.handler.entity.RefundResult;

/**
 * 微信交易处理类
 * @author Zer01ne
 * @since 2020/11/28 23:53
 */
@PayStrategy(PaymentMethodEnum.WX)
public class WeChatTradeStrategy extends AbstractTradeStrategy {
    @Override
    public PayResult pay(PayParam payParam) {
        return null;
    }

    @Override
    public RefundResult refund(RefundParam refundParam) {
        return null;
    }
}
