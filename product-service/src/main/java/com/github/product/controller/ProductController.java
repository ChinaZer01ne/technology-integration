package com.github.product.controller;

import com.github.product.entity.vo.ProductVO;
import com.github.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
