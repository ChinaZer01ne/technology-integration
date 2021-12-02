package com.github.product.service;

import com.github.common.core.response.PageResult;
import com.github.product.entity.dto.ProductCommentDTO;
import com.github.product.entity.dto.ProductCommentFavoriteDTO;
import com.github.product.param.CommentPageQueryParam;

import java.util.List;

/**
 * 评论服务
 * @author peach
 * @since 2021/12/2 0:23
 */
public interface CommentService {
    /**
     * 获取评论列表
     * @param commentPageQueryParam :
     * @return com.github.common.core.response.PageResult<java.lang.String>
     */
    PageResult<String> list(CommentPageQueryParam commentPageQueryParam);

    /**
     * 获取评论列表
     * @param productCommentDTO :
     * @return com.github.common.core.response.PageResult<java.lang.String>
     */
    boolean save(ProductCommentDTO productCommentDTO);

    /**
     * 逻辑删除课程评论
     * @param commentId :
     * @param userId :
     * @return boolean
     */
    boolean deleteCourseComment(String commentId, Integer userId);

    /**
     * 获取用户的评论
     * @param userId :
     * @return java.util.List<com.github.product.dto.ProductCommentDTO>
     */
    List<ProductCommentDTO> getUserById(Integer userId);

    /**
     * 评论点赞
     * @param userId :
     * @param commentId :
     * @return com.github.product.dto.ProductCommentFavoriteDTO
     */
    ProductCommentFavoriteDTO favorite(Integer userId, String commentId);
    /**
     * 评论取消点赞
     * @param userId :
     * @param commentId :
     * @return boolean
     */
    boolean cancelFavorite(Integer userId, String commentId);


}
