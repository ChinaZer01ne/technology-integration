package com.github.common.core.response;

import lombok.Data;

import java.util.List;

/**
 * 分页包装
 * @author peach
 * @since 2020/11/18 15:47
 */
@Data
public class PageResult<T> extends BaseResponse<T> {

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
    private Long totalCount;
    /**
     * 数据
     */
    private List<T> data;

    public static <T> PageResult<T> warp(List<T> data, Integer pageNum, Integer pageSize, Long size){
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setData(data);
        pageResult.setPageNum(pageNum);
        pageResult.setPageSize(pageSize);
        pageResult.setTotalCount(size);
        return pageResult;
    }
}