package com.github.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.user.entity.Permission;
import com.github.user.entity.User;

import java.util.List;

/**
 * @author Zer01ne
 * @since 2020/11/26 22:26
 */
public interface PermissionMapper extends BaseMapper<User> {
    /**
     * 根据用户名查询
     * @param userId: 用户名
     * @return com.github.user.entity.User
     */
    List<Permission> selectByUserId(Long userId);
}