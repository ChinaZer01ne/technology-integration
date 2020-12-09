package com.github.common.core.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器链
 * @author Zer01ne
 * @since 2020/12/9 20:07
 */
public class FilterChain<T> implements Filter<T> {

    private final List<Filter<T>> filters = new ArrayList<>();

    @Override
    public boolean doFilter(T t) {
        for (Filter<T> filter : filters) {
            if (!filter.doFilter(t)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 添加过滤器
     * @param filter :
     */
    public FilterChain<T> add(Filter<T> filter) {
        filters.add(filter);
        return this;
    }

    public List<Filter<T>> getFilters() {
        return filters;
    }
}
