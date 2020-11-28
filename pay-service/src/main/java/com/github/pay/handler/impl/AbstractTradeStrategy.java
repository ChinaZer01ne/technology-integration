package com.github.pay.handler.impl;

import com.github.pay.enums.PaymentMethodEnum;
import com.github.pay.handler.TradeStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付通用功能抽取
 * @author Zer01ne
 * @since 2020/11/29 0:06
 */
public abstract class AbstractTradeStrategy implements TradeStrategy {

    private Map<PaymentMethodEnum, TradeStrategy> tradeHandlerMap = new HashMap<>(16);

}
