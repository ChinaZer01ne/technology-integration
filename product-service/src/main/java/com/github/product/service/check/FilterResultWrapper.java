package com.github.product.service.check;

/**
 * 责任链结果
 * @author peach
 * @since 2021/5/19 15:35
 */
public class FilterResultWrapper {
    /**
     * 是否执行成功
     */
    private boolean isSuccess;
    /**
     * 过滤器结果
     */
    private String filterResult;
    /**
     * 返回信息
     */
    private String message;

    public FilterResultWrapper(boolean isSuccess, String filterResult) {
        this.isSuccess = isSuccess;
        this.filterResult = filterResult;
    }

    public FilterResultWrapper() {
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getFilterResult() {
        return filterResult;
    }

    public void setFilterResult(String filterResult) {
        this.filterResult = filterResult;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
