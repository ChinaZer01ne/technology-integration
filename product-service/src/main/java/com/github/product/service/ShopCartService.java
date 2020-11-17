package com.github.product.service;

import com.github.product.entity.input.CartRequest;
import com.github.product.entity.vo.CartVO;

/**
 * @author peach
 * @since 2020/11/17 13:45
 */
public interface ShopCartService {
    /**
     * 添加购物车
     * 1、如果用户未登录，则为购物车生成全局唯一id，以此为key，购物车数据为value存储到redis中，有效时间3个月，然后将数据写入cookie
     * 2、如果用户登录，则为购物车生成全局唯一id，以此为key，购物车数据为value存储到redis中，有效时间3个月，然后将数据写入cookie
     *
     * @param cartRequest : 购物车请求
     * @return com.github.product.entity.vo.CartVO
     */
    CartVO addCart(CartRequest cartRequest);

    /**
     * 删除购物车数据
     *
     * @param cartRequest : 购物车请求
     * @return com.github.product.entity.vo.CartVO
     */
    CartVO deleteCart(CartRequest cartRequest);

    /**
     * 合并购物车
     * 如果用户登录，则为redis购物车数据与用户购物车数据合并
     *
     * @param cartId 购物车id
     * @return com.github.product.entity.vo.CartVO
     */
    CartVO mergeCart(Long cartId);

    /**
     * 获取购物车信息
     * @param cartId :
     * @return com.github.product.entity.vo.CartVO
     */
    CartVO get(Long cartId);
}