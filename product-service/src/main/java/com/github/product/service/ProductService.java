package com.github.product.service;

import com.github.product.entity.vo.ProductVO;

import java.util.List;

/**
 * @author Zer01ne
 * @since 2020/11/15 19:04
 */
public interface ProductService {

    /**
     * 获取所有商品信息
     * @return java.util.List<com.github.product.entity.vo.ProductVO> 
     */
    List<ProductVO> getAll();

    /**
     * 获取指定商品
     * @param id : 主键
     * @return com.github.product.entity.vo.ProductVO
     */
    ProductVO get(Long id);
}
