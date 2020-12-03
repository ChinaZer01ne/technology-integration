package com.github.common.core.exception;

import lombok.Data;

/**
 * 异常明细信息
 * @author peach
 * @since 2020/11/19 15:51
 */
@Data
public class ExceptionDetail {

    /**
     * 异常参数
     */
    private Object[] params;
    /**
     * 异常路径
     */
    private StackTraceElement[] trace;
}