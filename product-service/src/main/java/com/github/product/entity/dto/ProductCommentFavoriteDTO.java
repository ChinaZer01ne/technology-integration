package com.github.product.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * 评论点赞dto
 * @author peach
 * @since 2021/12/2 23:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCommentFavoriteDTO {

    private String id;

    private Integer userId;

    private String commentId;

    private Boolean isDelete;

    private Date createTime;

    private Date updateTime;
}