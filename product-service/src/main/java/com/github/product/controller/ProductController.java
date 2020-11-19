package com.github.product.controller;

import com.github.common.ServerResponse;
import com.github.product.entity.vo.ProductVO;
import com.github.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Zer01ne
 * @since 2020/11/15 19:03
 */
@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation("商品浏览量统计/文章浏览量统计")
    @GetMapping("/view")
    public ServerResponse<ProductVO> view(@ApiParam("主键") @RequestParam("id") Long id){
        return ServerResponse.success(productService.view(id));
    }

    @ApiOperation("商品变价")
    @PutMapping("/price/change")
    public ServerResponse<Boolean> changPrice(@ApiParam("主键") @RequestParam("id") Long id, @ApiParam("目标价格") @RequestParam("price") Integer price){
        return ServerResponse.success(productService.changPrice(id, price));
    }

}
