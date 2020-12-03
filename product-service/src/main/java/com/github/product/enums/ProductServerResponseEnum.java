package com.github.product.enums;


import com.github.common.core.ServerResponseEnum;

/**
 * @author peach
 * @since 2020/11/18 11:08
 */
public enum ProductServerResponseEnum implements ServerResponseEnum {

    PARAM_ERROR(4001, "参数错误！");
    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String message;

    ProductServerResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}