package com.github.pay.annotation;

import com.github.pay.enums.PaymentMethodEnum;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解标识一个支付策略类
 * @author Zer01ne
 * @since 2020/11/29 0:26
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface PayStrategy {

    PaymentMethodEnum value();
}
