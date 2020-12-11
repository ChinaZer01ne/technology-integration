package com.github.internal.api.product.fallback;

import com.github.internal.api.product.ProductClient;
import com.github.internal.api.product.ProductDTO;

import java.util.List;

/**
 * @author peach
 * @since 2020/12/11 11:13
 */
public class ProductClientFallback implements ProductClient {
    @Override
    public ProductDTO get(Long id) {
        return null;
    }

    @Override
    public List<ProductDTO> get(List<Long> ids) {
        return null;
    }
}