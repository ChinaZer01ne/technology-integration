package com.github.product.service;

import com.github.common.Pageable;
import com.github.common.ServerResponse;
import com.github.product.entity.vo.ProductVO;
import com.github.product.entity.vo.SpecialPriceProductVO;

import java.util.List;

/**
 * @author peach
 * @since 2020/11/15 19:04
 */
public interface SpecialPriceService {

    /**
     * 获取特价商品详情
     * @param id : 主键
     * @return com.github.product.entity.vo.SpecialPriceProductVO
     */
    SpecialPriceProductVO get(Long id);

    /**
     * 获取特价商品列表并分页
     * @return com.github.common.Pageable<com.github.product.entity.vo.SpecialPriceProductVO>
     */
    Pageable<SpecialPriceProductVO> list();
}
