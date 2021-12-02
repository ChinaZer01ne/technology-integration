package com.github.common.core.response;

import lombok.Data;

/**
 * @author peach
 * @since 2021/12/2 0:34
 */
@Data
public class BaseResponse<T> {

    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String message;

}