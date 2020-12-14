package com.github.product.controller.internal;

import com.github.internal.api.product.ProductDTO;
import com.github.product.service.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author peach
 * @since 2020/12/11 11:26
 */
@RestController
@RequestMapping("/internal/product")
public class InnerProductController {
    @Autowired
    private ProductQueryService productQueryService;

    /**
     * 获取商品信息
     * @param id : 商品id
     * @return com.github.internal.api.product.ProductDTO
     */
    @GetMapping("/get")
    public ProductDTO get(@RequestParam("id") Long id) {
        return productQueryService.get(id);
    }

    /**
     * 获取商品信息
     * @param ids : 商品id集合
     * @return com.github.internal.api.product.ProductDTO
     */
    @GetMapping("/getByIds")
    public ProductDTO list(@RequestParam("ids") List<Long> ids) {
        return productQueryService.get(ids);
    }
}