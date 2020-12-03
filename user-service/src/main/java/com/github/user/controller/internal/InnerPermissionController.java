package com.github.user.controller.internal;

import com.github.internal.api.user.dto.PermissionDTO;
import com.github.user.entity.Permission;
import com.github.user.service.PermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author peach
 * @since 2020/11/26 16:05
 */
@RestController
@RequestMapping("/internal/permission")
public class InnerPermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/get")
    public List<PermissionDTO> get(@RequestParam("userId") Long userId) {
        List<Permission> permissions = permissionService.get(userId);

        List<PermissionDTO> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(permissions)) {
            for (Permission permission : permissions) {
                PermissionDTO permissionDTO = new PermissionDTO();
                BeanUtils.copyProperties(permission, permissionDTO);
                result.add(permissionDTO);
            }
        }
        return result;
    }
}