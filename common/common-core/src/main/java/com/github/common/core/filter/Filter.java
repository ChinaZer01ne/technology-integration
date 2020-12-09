package com.github.common.core.filter;
/**
 * 责任链模式
 * @author Zer01ne
 * @since 2020/12/9 20:06
 */
@FunctionalInterface
public interface Filter<T> {

    /**
     * 过滤方法
     * @param t 参数
     * @return boolean
     */
    boolean doFilter(T t);
}
