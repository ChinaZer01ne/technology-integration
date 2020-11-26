package com.github.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.user.entity.User;

/**
 * @author peach
 * @since 2020/11/25 17:19
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户名查询
     * @param username: 用户名
     * @return com.github.user.entity.User
     */
    User selectByUsername(String username);
}