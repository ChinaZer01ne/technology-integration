package com.github.internal.api.user;

import com.github.internal.api.user.dto.UserDTO;
import com.github.internal.api.user.fallback.UserClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author peach
 * @since 2020/11/26 16:09
 */
@FeignClient(name = "user-service", contextId = "userClient", fallback = UserClientFallback.class)
public interface UserClient {

    /**
     * 根据用户名获取密码
     * @param username : 用户名
     * @return com.github.internal.api.user.dto.UserDTO
     */
    @GetMapping("/internal/user/get")
    UserDTO get(@RequestParam("username") String username);
}