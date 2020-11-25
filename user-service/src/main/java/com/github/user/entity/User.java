package com.github.user.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author peach
 * @since 2020/11/23 17:21
 */
@Data
public class User {

    /**
     * id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 创建时间
     */
    private LocalDateTime created;
    /**
     * 更新时间
     */
    private LocalDateTime updated;
}