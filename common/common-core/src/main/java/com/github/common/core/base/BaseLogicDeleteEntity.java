package com.github.common.core.base;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author peach
 * @since 2021/12/3 0:08
 */
@Data
public class BaseLogicDeleteEntity extends BaseEntity {
    /**
     * 是否删除
     */
    private Integer isDelete;

}
