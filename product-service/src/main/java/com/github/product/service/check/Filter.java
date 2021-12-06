package com.github.product.service.check;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 责任链
 * @author peach
 * @since 2021/5/19 16:53
 */
public abstract class Filter {

    protected static final Logger logger = LoggerFactory.getLogger(Filter.class);
    /**
     * 下一个节点
     */
    private Filter next;

    public FilterResultWrapper filter(Task task) {
        return new FilterResultWrapper();
    }

    protected Filter append(Filter next) {
        this.next = next;
        return next;
    }

    protected Filter next() {
        return next;
    }
}
