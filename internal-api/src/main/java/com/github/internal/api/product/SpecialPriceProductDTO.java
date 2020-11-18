package com.github.internal.api.product;

import lombok.Data;

/**
 * 特殊商品定时推送redis实体
 * @author peach
 * @since 2020/11/18 16:48
 */
@Data
public class SpecialPriceProductDTO {
    /**
     * id
     * */
    private Long id;
    /**
     * 名称
     * */
    private String name;
    /**
     * 价格
     * */
    private Integer price;
    /**
     * 浏览量
     * */
    private Long views;
}