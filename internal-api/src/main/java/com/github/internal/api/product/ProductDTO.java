package com.github.internal.api.product;

import lombok.Data;

/**
 * @author peach
 * @since 2020/12/11 11:16
 */
@Data
public class ProductDTO {
    /**
     * 商品id
     */
    private Long id;
    /**
     * 商品名
     */
    private String productName;
    /**
     * 商品数量
     */
    private Integer productNum;
    /**
     * 商品价格
     */
    private Integer productPrice;
}