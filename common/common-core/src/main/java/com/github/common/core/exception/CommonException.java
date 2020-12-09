package com.github.common.core.exception;

import com.github.common.core.response.ServerResponseEnum;
import lombok.Data;

/**
 * 项目顶级异常类
 * @author peach
 * @since 2020/11/19 15:37
 */
@Data
public class CommonException extends RuntimeException {

    /**
     * 异常相应枚举
     */
    private ServerResponseEnum responseEnum;
    /**
     * 异常明细：这里需要一个异常信息展示的开关，可能放在网关层去做
     */
    private ExceptionDetail exceptionDetail;

    public CommonException() {
        super();
    }

    public CommonException(ServerResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    protected CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CommonException showMore() {
        if (exceptionDetail == null) {
            exceptionDetail = new ExceptionDetail();
        }
        exceptionDetail.setTrace(this.getStackTrace());
        return this;
    }

    public CommonException showMore(Object... params) {
        showMore();
        exceptionDetail.setParams(params);
        return this;
    }
}