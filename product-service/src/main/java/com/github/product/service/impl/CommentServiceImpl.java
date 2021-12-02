package com.github.product.service.impl;

import com.github.common.core.response.PageResult;
import com.github.product.entity.dto.ProductCommentDTO;
import com.github.product.entity.dto.ProductCommentFavoriteDTO;
import com.github.product.param.CommentPageQueryParam;
import com.github.product.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author peach
 * @since 2021/12/2 0:24
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Override
    public PageResult<String> list(CommentPageQueryParam commentPageQueryParam) {
        return null;
    }

    @Override
    public boolean save(ProductCommentDTO productCommentDTO) {
        return false;
    }

    @Override
    public boolean deleteCourseComment(String commentId, Integer userId) {
        return false;
    }

    @Override
    public List<ProductCommentDTO> getUserById(Integer userId) {
        return null;
    }

    @Override
    public ProductCommentFavoriteDTO favorite(Integer userId, String commentId) {
        return null;
    }

    @Override
    public boolean cancelFavorite(Integer userId, String commentId) {
        return false;
    }
}
