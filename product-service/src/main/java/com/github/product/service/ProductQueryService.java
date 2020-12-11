package com.github.product.service;

import com.github.internal.api.product.ProductDTO;

/**
 * @author Zer01ne
 * @since 2020/11/15 19:04
 */
public interface ProductQueryService {

    /**
     * 获取商品信息
     * @param id :
     * @return com.github.internal.api.product.ProductDTO
     */
    ProductDTO get(Long id);
}
