package com.github.product.controller;

import com.github.common.ServerResponse;
import com.github.product.entity.input.CartRequest;
import com.github.product.entity.vo.CartVO;
import com.github.product.service.ShopCartService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @author peach
 * @since 2020/11/17 13:44
 */
@RestController
@RequestMapping("/cart")
public class ShopCartController {

    private final ShopCartService shopCartService;

    public ShopCartController(ShopCartService shopCartService) {
        this.shopCartService = shopCartService;
    }

    @ApiOperation("添加购物车")
    @PostMapping("/add")
    public ServerResponse<CartVO> addCart(CartRequest cartRequest){
        return ServerResponse.success(shopCartService.addCart(cartRequest));
    }

    @ApiOperation("删除购物车")
    @DeleteMapping("/delete")
    public ServerResponse<CartVO> deleteCart(CartRequest cartRequest){
        return ServerResponse.success(shopCartService.deleteCart(cartRequest));
    }

    @ApiOperation("合并购物车")
    @PutMapping("/merge")
    public ServerResponse<CartVO> mergeCart(@ApiParam("购物车id") @RequestParam("cartId") Long cartId){
        return ServerResponse.success(shopCartService.mergeCart(cartId));
    }

    @ApiOperation("查询购物车")
    @GetMapping("/get")
    public ServerResponse<CartVO> get(@ApiParam("购物车id") @RequestParam("cartId") Long cartId){
        return ServerResponse.success(shopCartService.get(cartId));
    }
}