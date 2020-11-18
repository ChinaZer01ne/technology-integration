package com.github.common;

import lombok.Data;

import java.util.List;

/**
 * 分页包装
 * @author peach
 * @since 2020/11/18 15:47
 */
@Data
public class Pageable<T> {

    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 每页大小
     */
    private Integer pageSize;
    /**
     * 总记录
     */
    private Integer totalCount;

    /**
     * 数据
     */
    private List<T> data;
}