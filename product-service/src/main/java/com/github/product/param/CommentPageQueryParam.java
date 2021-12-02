package com.github.product.param;

import com.github.common.core.base.PageQuery;
import lombok.Data;

/**
 * @author peach
 * @since 2021/12/2 23:42
 */
@Data
public class CommentPageQueryParam extends PageQuery {
    /**
     * 商品id
     */
    private String productId;
}
