package com.github.pay.enums;

import java.util.Objects;

/**
 * 支付方式枚举
 * @author Zer01ne
 * @since 2020/11/28 23:40
 */
public enum  PaymentMethodEnum {
    Ali(1,"支付宝"),
    WX(2,"微信");

    private Integer code;
    private String name;

    PaymentMethodEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 根据支付方式编码获取对应枚举
     * @param paymentMethod :
     * @return com.github.pay.enums.PaymentMethodEnum
     */
    public static PaymentMethodEnum get(Integer paymentMethod) {
        PaymentMethodEnum[] values = PaymentMethodEnum.values();
        for (PaymentMethodEnum value : values) {
            if (Objects.equals(value.code,  paymentMethod)) {
                return value;
            }
        }
        throw new IllegalArgumentException("No method of payment find!");
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
