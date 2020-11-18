package com.github.product.service;

import com.github.common.Pageable;
import com.github.common.ServerResponse;
import com.github.product.entity.vo.ProductVO;
import com.github.product.entity.vo.SpecialPriceProductVO;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author peach
 * @since 2020/11/15 19:04
 */
public interface SpecialPriceService {

    /**
     * 获取特价商品列表并分页
     * @param pageNum : 当前页
     * @param pageSize ：页大小
     * @return com.github.common.Pageable<com.github.product.entity.vo.SpecialPriceProductVO>
     */
    Pageable<SpecialPriceProductVO> list(Integer pageNum, Integer pageSize);
}
