package com.github.internal.api.user.fallback;

import com.github.internal.api.user.PermissionClient;
import com.github.internal.api.user.dto.PermissionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author peach
 * @since 2020/12/3 10:49
 */
@Slf4j
@Component
public class PermissionClientFallback implements PermissionClient {


    @Override
    public List<PermissionDTO> get(Long userId) {
        log.error("用户服务熔断触发。。。");
        return new ArrayList<>();
    }
}