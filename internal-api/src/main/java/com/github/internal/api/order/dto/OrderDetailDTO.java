package com.github.internal.api.order.dto;

import lombok.Data;

/**
 * @author peach
 * @since 2020/12/11 14:29
 */
@Data
public class OrderDetailDTO {

    /**
     * 商品id
     */
    private Long productId;
    /**
     * 商品数量
     */
    private Integer productNum;
}