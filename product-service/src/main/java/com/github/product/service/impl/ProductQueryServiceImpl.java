package com.github.product.service.impl;

import com.github.internal.api.product.ProductDTO;
import com.github.product.service.ProductQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    //@Autowired
    //private ProductMapper productMapper;

    @Override
    public ProductDTO get(Long id) {
        return null;
    }

    @Override
    public ProductDTO get(List<Long> ids) {
        return null;
    }
}