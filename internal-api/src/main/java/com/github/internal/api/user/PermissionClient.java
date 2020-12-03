package com.github.internal.api.user;

import com.github.internal.api.user.dto.PermissionDTO;
import com.github.internal.api.user.fallback.PermissionClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author peach
 * @since 2020/11/26 16:42
 */
@FeignClient(name = "user-service", contextId = "permissionClient", fallback = PermissionClientFallback.class)
public interface PermissionClient {

    /**
     * 获取用户权限信息
     * @param userId :
     * @return java.util.List<com.github.internal.api.user.dto.PermissionDTO>
     */
    @GetMapping("/internal/permission/get")
    List<PermissionDTO> get(@RequestParam("userId") Long userId);
}