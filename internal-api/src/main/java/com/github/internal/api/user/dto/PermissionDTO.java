package com.github.internal.api.user.dto;

import lombok.Data;

/**
 * @author peach
 * @since 2020/11/26 16:45
 */
@Data
public class PermissionDTO {
    /**
     * id
     * */
    private String id;
    /**
     * parent id
     * */
    private String parentId;
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
    private String created;
    /**
     * 修改时间
     * */
    private String updated;
}