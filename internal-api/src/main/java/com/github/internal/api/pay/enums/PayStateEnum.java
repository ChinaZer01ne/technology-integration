package com.github.internal.api.pay.enums;

import java.util.Objects;

/**
 * 支付状态枚举
 * @author Zer01ne
 * @since 2020/11/28 23:40
 */
public enum PayStateEnum {
    SUCCESS(1,"支付成功"),
    FAILURE(2,"支付失败");

    private Integer code;
    private String name;

    PayStateEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 根据支付方式编码获取对应枚举
     * @param paymentMethod :
     * @return com.github.pay.enums.PaymentMethodEnum
     */
    public static PayStateEnum get(Integer paymentMethod) {
        PayStateEnum[] values = PayStateEnum.values();
        for (PayStateEnum value : values) {
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
