package com.github.internal.api.user;

import com.github.internal.api.user.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author peach
 * @since 2020/11/26 16:09
 */
@FeignClient(name = "user-service")
@RequestMapping("/internal/user")
public interface UserClient {

    /**
     * 根据用户名获取密码
     * @param username : 用户名
     * @return User
     */
    @GetMapping("/get")
    UserDTO get(@RequestParam("username") String username);
}