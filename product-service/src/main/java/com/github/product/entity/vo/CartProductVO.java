package com.github.product.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * TODO 在业务上前端请求的入参和对应的出参几乎是一样的，因为是同一个点触犯，展示同样数据，可以统一使用VO，而不用分的很细，
 * @author peach
 * @since 2020/11/17 17:05
 */
@ApiModel("购物车商品展示对象")
@Data
public class CartProductVO {
    /**
     * 购物车id
     */
    @ApiParam("购物车id")
    @ApiModelProperty("购物车id")
    private Long cartId;
    /**
     * 用户id
     */
    @ApiParam("用户id")
    @ApiModelProperty("用户id")
    private Long userId;
    /**
     * 商品id
     */
    @ApiParam("商品id")
    @ApiModelProperty("商品id")
    private Long productId;
    /**
     * 商品数量
     */
    @ApiParam("商品数量")
    @ApiModelProperty("商品数量")
    private Integer productNum;
}