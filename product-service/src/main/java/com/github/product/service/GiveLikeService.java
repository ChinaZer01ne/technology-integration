package com.github.product.service;

import com.github.product.entity.vo.GiveLikeVO;

/**
 * 点赞服务
 * @author Zer01ne
 * @since 2020/11/21 13:36
 */
public interface GiveLikeService {

    /**
     * @param productId:商品id
     * @param userId:用户id
     */
    GiveLikeVO get(Long productId, Long userId);
    /**
     * 点赞或者取消点赞
     * @param id : 商品id
     * @param userId :用户id
     */
    GiveLikeVO click(Long id, Long userId);
}
