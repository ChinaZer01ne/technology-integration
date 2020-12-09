package com.github.common.core.response;

import lombok.Data;

/**
 * @author peach
 * @since 2020/11/18 10:42
 */
@Data
public class ServerResponse<T> {

    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    public static <T> ServerResponse<T> success(Integer code, String message, T data) {
        ServerResponse<T> serverResponse = new ServerResponse<>();
        serverResponse.setCode(code);
        serverResponse.setMessage(message);
        serverResponse.setData(data);
        return serverResponse;
    }

    public static <T> ServerResponse<T> success(T data) {
        return success(CommonServerResponseEnum.SUCCESS.getCode(), CommonServerResponseEnum.SUCCESS.getMessage(), data);
    }

    public static <T> ServerResponse<T> success(ServerResponseEnum responseEnum, T data) {
        return success(responseEnum.getCode(), responseEnum.getMessage(), data);
    }

    public static <T> ServerResponse<T> fail(Integer code, String message, T data) {
        ServerResponse<T> serverResponse = new ServerResponse<>();
        serverResponse.setCode(code);
        serverResponse.setMessage(message);
        serverResponse.setData(data);
        return serverResponse;
    }

    public static <T> ServerResponse<T> fail(String message) {
        return fail(CommonServerResponseEnum.FAIL.getCode(), message, null);
    }

    public static <T> ServerResponse<T> fail(ServerResponseEnum responseEnum) {
        return fail(responseEnum.getCode(), responseEnum.getMessage(),null);
    }
}