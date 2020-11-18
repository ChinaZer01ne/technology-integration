package com.github.product.controller;

import com.github.common.Pageable;
import com.github.common.ServerResponse;
import com.github.product.entity.vo.SpecialPriceProductVO;
import com.github.product.service.SpecialPriceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 特价商品（参考淘宝聚划算，京东拍拍道）
 * @author peach
 * @since 2020/11/18 16:07
 */
@RestController
@RequestMapping("/product/special")
public class SpecialPriceController {

    @Autowired
    private SpecialPriceService specialPriceService;

    @ApiOperation("获取特价商品详情")
    @RequestMapping("/get")
    public ServerResponse<SpecialPriceProductVO> get(@ApiParam("id") @RequestParam("id") Long id) {
        return ServerResponse.success(specialPriceService.get(id));
    }

    @ApiOperation("获取特价商品列表")
    @RequestMapping("/list")
    public ServerResponse<Pageable<SpecialPriceProductVO>> list() {
        return ServerResponse.success(specialPriceService.list());
    }
}