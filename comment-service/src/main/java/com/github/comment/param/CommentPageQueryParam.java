package com.github.comment.param;

import com.github.common.core.base.PageQuery;
import lombok.Data;

/**
 * @author peach
 * @since 2021/12/2 23:42
 */
@Data
public class CommentPageQueryParam extends PageQuery {
    /**
     * 话题id
     */
    private String topicId;
}
