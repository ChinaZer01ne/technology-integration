package com.github.comment.entity;

import com.github.common.core.base.BaseLogicDeleteEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品评论
 * @author peach
 * @since 2021/12/3 0:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseLogicDeleteEntity {
    /**
     * 主键
     */
    private String id;
    /**
     * 话题id（针对谁的评论）
     */
    private String topicId;
    /**
     * 应用id（是哪个应用的评论）
     */
    private Integer appId;
    /**
     * 用户id（谁发的）
     */
    private Integer fromUserId;
    /**
     * 发送人昵称
     */
    private String nickName;
    /**
     * 用户id（发给谁的）
     */
    private Integer toUserId;
    /**
     * 评论
     */
    private String comment;
    /**
     * 点赞数
     */
    private Integer likeCount;
    /**
     * 是否置顶
     */
    private Integer isTop;
}