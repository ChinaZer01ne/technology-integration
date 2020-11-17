package com.github.product.entity.input;

import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * 购物车VO
 * @author Zer01ne
 * @since 2020/11/15 19:06
 */
@Data
public class CartRequest {

    /**
     * 购物车id
     */
    @ApiParam("购物车id")
    private Long cartId;
    /**
     * 用户id
     */
    @ApiParam("用户id")
    private Long userId;
    /**
     * 商品id
     */
    @ApiParam("商品id")
    private Long productId;
    /**
     * 商品数量
     */
    @ApiParam("商品数量")
    private Integer productNum;
}
