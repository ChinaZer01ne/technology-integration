package com.github.user.service.impl;

import com.github.user.entity.User;
import com.github.user.mapper.UserMapper;
import com.github.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author peach
 * @since 2020/11/23 17:20
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public User get(String username) {
        return null;
    }
}