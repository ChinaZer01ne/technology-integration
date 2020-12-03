package com.github.internal.api.user.fallback;

import com.github.internal.api.user.UserClient;
import com.github.internal.api.user.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserClientFallback implements UserClient {

    @Override
    public UserDTO get(String username) {
        log.error("用户服务熔断触发。。。");
        return new UserDTO();
    }
}