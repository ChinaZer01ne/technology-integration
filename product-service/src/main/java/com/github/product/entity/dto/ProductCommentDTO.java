package com.github.product.entity.dto;

import lombok.Data;
/**
 * @author peach
 * @since 2021/12/2 23:49
 */
@Data
public class ProductCommentDTO {

    private String id;

    private Integer productId;

    private Integer userId;

    private String comment;

    private boolean isOwner = false;

    private String nickName;

    private boolean favoriteTag;

    private Integer likeCount;
}