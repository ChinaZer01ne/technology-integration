package com.github.user.controller.internal;

import com.github.internal.api.user.dto.UserDTO;
import com.github.user.entity.User;
import com.github.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peach
 * @since 2020/11/26 16:05
 */
@RestController
@RequestMapping("/internal/user")
public class InnerUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public UserDTO get(@RequestParam("username") String username) {
        User user = userService.get(username);
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }
}