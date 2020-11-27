package com.github.user.service;

import com.github.user.entity.User;

import java.util.List;

/**
 * @author peach
 * @since 2020/11/23 17:19
 */
public interface UserService {

    /**
     * 获取用户信息
     * @param id :
     * @return com.github.user.entity.User
     */
    User get(Long id);
    /**
     * 获取用户信息
     * @param username : 用户名
     * @return com.github.user.entity.User
     */
    User get(String username);

    /**
     * 查询用户列表
     * @return java.util.List<com.github.user.entity.User>
     */
    List<User> list();
}