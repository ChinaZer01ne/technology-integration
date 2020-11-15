package com.github.product.service.impl;

import com.github.product.entity.Product;
import com.github.product.entity.vo.ProductVO;
import com.github.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zer01ne
 * @since 2020/11/15 19:04
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public List<ProductVO> getAll() {
        List<ProductVO> productVOList = new ArrayList<>();
        ProductVO productVO = new ProductVO();
        productVO.setId(1L);
        productVO.setName("机械键盘");
        productVO.setViews(1L);
        productVOList.add(productVO);
        return productVOList;
    }

    @Override
    public ProductVO get(Long id) {
        ProductVO productVO = new ProductVO();
        productVO.setId(1L);
        productVO.setName("机械键盘");
        productVO.setViews(1L);
        return productVO;
    }
}
