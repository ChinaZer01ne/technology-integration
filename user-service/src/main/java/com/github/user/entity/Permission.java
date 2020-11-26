package com.github.user.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Zer01ne
 * @since 2020/11/26 22:27
 */
@Data
public class Permission {
    /**
     * id
     * */
    private String id;
    /**
     * parent id
     * */
    private Long parentId;
    /**
     * 权限名称
     * */
    private String name;
    /**
     * 权限英文名称
     * */
    private String enName;
    /**
     * 权限控制url
     * */
    private String url;
    /**
     * 权限描述
     * */
    private String description;
    /**
     * 创建时间
     * */
    private LocalDateTime created;
    /**
     * 修改时间
     * */
    private LocalDateTime updated;
}
