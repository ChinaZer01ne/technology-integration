package com.github.product.service.check;
/**
 * 审核服务
 * @author peach
 * @since 2021/5/19 14:12
 */

import org.springframework.stereotype.Service;


@Service
public class ImageAuditFilter extends Filter {
    @Override
    public FilterResultWrapper filter(Task task) {

        Filter next = super.next();
        if (next == null) {
            return new FilterResultWrapper();
        }
        return next.filter(task);
    }
}
