package com.github.comment.entity.dto;

import lombok.Data;
/**
 * @author peach
 * @since 2021/12/2 23:49
 */
@Data
public class CommentDTO {

    private String id;

    private String topicId;

    private Integer userId;

    private String comment;

    private boolean isOwner = false;

    private String nickName;

    private boolean favoriteTag;

    private Integer likeCount;
}