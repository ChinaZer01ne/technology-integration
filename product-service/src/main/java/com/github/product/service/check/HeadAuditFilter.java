package com.github.product.service.check;


import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 责任链头节点
 * @author peach
 * @since 2021/5/19 14:12
 */

@Service
public class HeadAuditFilter extends Filter {

    @Override
    public FilterResultWrapper filter(Task task) {
        Filter next = super.next();
        Assert.notNull(next, "责任链配置错误！");
        return next.filter(task);
    }
}
