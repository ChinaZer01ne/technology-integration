package com.github.internal.api.product;

import com.github.internal.api.product.fallback.ProductClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author peach
 * @since 2020/12/11 11:12
 */
@FeignClient(name = "product-service", contextId = "productClient", fallback = ProductClientFallback.class)
public interface ProductClient {

    /**
     * 获取商品
     * @param id : 商品id
     * @return com.github.internal.api.product.ProductDTO
     */
    @GetMapping("/internal/product/get")
    ProductDTO get(@RequestParam("id") Long id);

    /**
     * 获取商品
     * @param ids : 商品id集合
     * @return com.github.internal.api.product.ProductDTO
     */
    @GetMapping("/internal/product/getByIds")
    List<ProductDTO> get(@RequestParam("ids") List<Long> ids);
}