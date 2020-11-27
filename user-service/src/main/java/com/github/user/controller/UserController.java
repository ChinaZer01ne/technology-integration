package com.github.user.controller;

import com.github.user.entity.User;
import com.github.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author peach
 * @since 2020/11/26 16:05
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户
     * @param username :
     * @return reactor.core.publisher.Mono<com.github.user.entity.User>
     */
    @GetMapping("/get")
    public Mono<User> get(@RequestParam("username") String username) {
        return Mono.create(userMonoSink -> {
            User user = userService.get(username);
            userMonoSink.success(user);
        });
    }

    /**
     * 获取用户
     * @return reactor.core.publisher.Mono<com.github.user.entity.User>
     */
    @GetMapping("/list")
    public Flux<User> list() {
        return Flux.fromIterable(userService.list());
    }
}