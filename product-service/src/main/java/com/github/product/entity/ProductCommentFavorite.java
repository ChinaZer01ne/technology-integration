package com.github.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 * 商品评论点赞
 * @author peach
 * @since 2021/12/3 0:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCommentFavorite {
    /**
     * 主键
     */
    private String id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 评论id
     */
    private String commentId;
    /**
     * 是否删除
     */
    private Boolean isDelete;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}