package com.github.product.controller;

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
    public ProductVO view(@ApiParam("主键") @RequestParam("id") Long id){
        return productService.view(id);
    }

    @ApiOperation("商品变价")
    @PutMapping("/price/change")
    public Boolean changPrice(@ApiParam("主键") @RequestParam("id") Long id, @ApiParam("目标价格") @RequestParam("price") Integer price){
        return productService.changPrice(id, price);
    }

    @ApiOperation("获取所有商品")
    @GetMapping("/list")
    public List<ProductVO> getAll(){
        return productService.getAll();
    }

    @ApiOperation("获取指定商品")
    @GetMapping("/get")
    public ProductVO get(@ApiParam("主键") @RequestParam("id") Long id){
        return productService.get(id);
    }
}
