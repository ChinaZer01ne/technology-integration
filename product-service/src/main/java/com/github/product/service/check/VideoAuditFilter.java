package com.github.product.service.check;

import org.springframework.stereotype.Service;

/**
 * 视频审核节点
 * @author peach
 * @since 2021/5/19 14:12
 */

@Service
public class VideoAuditFilter extends Filter {


    @Override
    public FilterResultWrapper filter(Task task) {
        Filter next = super.next();
        if (next == null) {
            return new FilterResultWrapper();
        }
        return next.filter(task);
    }
}
