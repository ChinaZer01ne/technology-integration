package com.github.user.service;

import com.github.internal.api.user.dto.PermissionDTO;
import com.github.user.entity.Permission;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Zer01ne
 * @since 2020/11/26 22:25
 */
public interface PermissionService {

    /**
     * 获取用户权限信息
     * @param userId :
     * @return java.util.List<com.github.internal.api.user.dto.PermissionDTO>
     */
    List<Permission> get(Long userId);
}
