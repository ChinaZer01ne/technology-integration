package com.github.product.service;

import com.github.product.entity.vo.ProductVO;

/**
 * @author Zer01ne
 * @since 2020/11/15 19:04
 */
public interface ProductService {


    /**
     * 统计商品的访问量
     * @param id : 主键
     * @return com.github.product.entity.vo.ProductVO
     */
    Boolean view(Long id);

    /**
     * 商品变价
     * @param id : 主键
     * @param price : 目标
     * @return java.lang.Boolean
     */
    Boolean changPrice(Long id, Integer price);
    /**
     * 查看pv访问量
     * @param id :
     * @return java.lang.Object
     */
    ProductVO viewCount(Long id);
    /**
     * 是否是黑名单用户
     * @param userId : 用户id
     * @return java.lang.Boolean
     */
    Boolean isBlack(Long userId);
}
