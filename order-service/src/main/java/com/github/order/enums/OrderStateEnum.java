package com.github.order.enums;

/**
 * 订单状态枚举
 * @author Zer01ne
 * @since 2020/11/29 0:48
 */
public enum  OrderStateEnum {
    PRE(-100,"预创建"),
    UNPAID(100,"待付款"),
    UN_SEND(200,"待发货"),
    UN_RECEIVED(300,"待收货"),
    FINISH(400,"已完成"),
    CANCEL(500,"已取消");

    private Integer code;
    private String name;

    OrderStateEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
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
