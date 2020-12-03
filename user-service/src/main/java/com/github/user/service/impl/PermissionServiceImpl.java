package com.github.user.service.impl;

import com.github.user.entity.Permission;
import com.github.user.mapper.PermissionMapper;
import com.github.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zer01ne
 * @since 2020/11/26 22:25
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> get(Long userId) {
        return permissionMapper.selectByUserId(userId);
    }
}
