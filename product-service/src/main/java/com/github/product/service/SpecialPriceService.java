package com.github.product.service;

import com.github.common.core.response.Pageable;
import com.github.product.entity.vo.SpecialPriceProductVO;

/**
 * @author peach
 * @since 2020/11/15 19:04
 */
public interface SpecialPriceService {

    /**
     * 获取特价商品列表并分页
     * @param pageNum : 当前页
     * @param pageSize ：页大小
     * @return com.github.common.core.response.Pageable<com.github.product.entity.vo.SpecialPriceProductVO>
     */
    Pageable<SpecialPriceProductVO> list(Integer pageNum, Integer pageSize);
}
