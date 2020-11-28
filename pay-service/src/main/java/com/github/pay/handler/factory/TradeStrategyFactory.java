package com.github.pay.handler.factory;

import com.github.pay.enums.PaymentMethodEnum;
import com.github.pay.handler.TradeStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付策略工厂
 * @author Zer01ne
 * @since 2020/11/29 0:12
 */
public class TradeStrategyFactory {

    private static Map<PaymentMethodEnum, TradeStrategy> tradeStrategyMap = new HashMap<>(16);

    /**
     * 生成支付策略
     * @param paymentMethodEnum : 支付枚举
     * @return com.github.pay.handler.TradeStrategy
     */
    public static TradeStrategy generateStrategy(PaymentMethodEnum paymentMethodEnum) {
        return tradeStrategyMap.get(paymentMethodEnum);
    }

    /**
     * 添加支付策略
     * @param paymentMethodEnum : 支付枚举
     * @param tradeStrategy : 支付策略
     */
    public static void add(PaymentMethodEnum paymentMethodEnum, TradeStrategy tradeStrategy) {
        tradeStrategyMap.put(paymentMethodEnum, tradeStrategy);
    }
}
