package com.github.pay.handler;

import com.github.pay.handler.entity.PayParam;
import com.github.pay.handler.entity.PayResult;
import com.github.pay.handler.entity.RefundParam;
import com.github.pay.handler.entity.RefundResult;

/**
 * 交易处理类
 * @author Zer01ne
 * @since 2020/11/28 23:47
 */
public interface TradeStrategy {

    /**
     * 支付
     * @param payParam :
     * @return com.github.pay.handler.entity.PayResult
     */
    PayResult pay(PayParam payParam);

    /**
     * 退款
     * @param refundParam :
     * @return com.github.pay.handler.entity.RefundResult
     */
    RefundResult refund(RefundParam refundParam);
}
