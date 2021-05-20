package com.github.product.service.check;

import org.springframework.stereotype.Service;

/**
 * 文本审核节点
 * @author peach
 * @since 2021/5/19 14:12
 */


@Service
public class TextAuditFilter extends Filter {

    @Override
    public FilterResultWrapper filter(Task task) {

        Filter next = super.next();
        if (next == null) {
            return new FilterResultWrapper(true, "PASS");
        }
        return next.filter(task);
    }
}
