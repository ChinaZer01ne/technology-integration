package com.github.product.service.impl;

import com.github.common.Pageable;
import com.github.product.entity.vo.SpecialPriceProductVO;
import com.github.product.service.SpecialPriceService;
import org.springframework.stereotype.Service;

/**
 * @author peach
 * @since 2020/11/18 16:22
 */
@Service
public class SpecialPriceServiceImpl implements SpecialPriceService {
    @Override
    public SpecialPriceProductVO get(Long id) {
        return null;
    }

    /**
     * 这种特价商品场景一般并发量比较大，其中的商品数据一般是预加载到redis中，请求主要是redis来处理。
     */
    @Override
    public Pageable<SpecialPriceProductVO> list() {
        return null;
    }
}