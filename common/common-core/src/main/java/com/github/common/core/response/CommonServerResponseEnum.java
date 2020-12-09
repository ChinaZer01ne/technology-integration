package com.github.common.core.response;


/**
 * @author peach
 * @since 2020/11/18 10:42
 */
public enum  CommonServerResponseEnum implements ServerResponseEnum {

    SUCCESS(200,"操作成功!"),
    FAIL(500,"操作失败！");

    /**
     * 响应码
     * */
    private Integer code;
    /**
     * 响应消息
     * */
    private String message;

    CommonServerResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}