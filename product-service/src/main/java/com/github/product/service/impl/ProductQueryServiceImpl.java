package com.github.product.service.impl;

import com.github.internal.api.product.ProductDTO;
import com.github.product.service.ProductQueryService;
import org.springframework.stereotype.Service;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    //@Autowired
    //private ProductMapper productMapper;

    @Override
    public ProductDTO get(Long id) {
        return null;
    }
}