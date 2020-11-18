package com.github.product.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Zer01ne
 * @since 2020/11/15 19:06
 */
@Data
public class Product {

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
