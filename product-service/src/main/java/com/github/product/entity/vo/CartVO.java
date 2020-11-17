package com.github.product.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author peach
 * @since 2020/11/17 17:04
 */
@ApiModel("购物车展示对象")
@Data
public class CartVO {
    /**
     * 购物车商品集合
     */
    @ApiModelProperty("购物车商品集合")
    List<CartProductVO> cartProductList;
}