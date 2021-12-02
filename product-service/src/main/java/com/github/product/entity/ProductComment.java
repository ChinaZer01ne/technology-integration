package com.github.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 * 商品评论
 * @author peach
 * @since 2021/12/3 0:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductComment {
    /**
     * 主键
     */
    private String id;
    /**
     * 商品id
     */
    private Integer productId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 点赞数
     */
    private Integer likeCount;
    /**
     * 评论
     */
    private String comment;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 是否删除
     */
    private Boolean isDelete;

}