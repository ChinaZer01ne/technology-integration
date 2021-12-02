package com.github.common.core.base;

import lombok.Data;

/**
 * @author peach
 * @since 2021/12/2 23:43
 */
@Data
public class PageQuery {
    /**
     * 页码
     */
    private Integer pageNum = 1;
    /**
     * 分页大小
     */
    private Integer pageSize = 20;
}
