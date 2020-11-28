package com.github.pay.handler.init;

import com.github.pay.annotation.PayStrategy;
import com.github.pay.handler.TradeStrategy;
import com.github.pay.handler.factory.TradeStrategyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Zer01ne
 * @since 2020/11/29 0:24
 */
@Component
public class TradeBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        PayStrategy annotation = bean.getClass().getAnnotation(PayStrategy.class);
        if (bean instanceof TradeStrategy && annotation != null) {
            TradeStrategyFactory.add(annotation.value(), (TradeStrategy) bean);
        }
        return bean;
    }
}
