package com.github.product.entity.vo;

import lombok.Data;

/**
 * @author Zer01ne
 * @since 2020/11/21 16:17
 */
@Data
public class GiveLikeVO {
    /**
     * 当前商品
     */
    private Long productId;
    /**
     * 点赞数
     */
    private Long giveLikeCount;
    /**
     * 当前用户是否点赞
     */
    private Integer isClick;
}
