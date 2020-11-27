package com.github.user.service.impl;

import com.github.user.entity.User;
import com.github.user.mapper.UserMapper;
import com.github.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return userMapper.selectById(id);
    }

    @Override
    public User get(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public List<User> list() {
        return userMapper.selectList(null);
    }
}