package com.github.order.entity.vo;

import lombok.Data;

/**
 * @author peach
 * @since 2020/12/11 11:03
 */
@Data
public class CartWareVO {
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 商品数量
     */
    private Integer productNum;

}