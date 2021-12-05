package com.github.common.core.base;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author peach
 * @since 2021/12/3 0:08
 */
@Data
public class BaseEntity {
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
