package com.github.product.controller;

import com.github.common.core.response.ServerResponse;
import com.github.product.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 黑名单校验
 * @author Zer01ne
 * @since 2020/11/21 9:17
 */
@RestController
@RequestMapping("/comment/black")
public class CommentBlackListController {

    private final ProductService productService;

    public CommentBlackListController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation("查询用户是否在黑名单内")
    @GetMapping("/isBlack")
    public ServerResponse<Boolean> isBlack(){
        // 获取访问的userId
        Long userId = 1000L;
        return ServerResponse.success(productService.isBlack(userId));
    }
}
