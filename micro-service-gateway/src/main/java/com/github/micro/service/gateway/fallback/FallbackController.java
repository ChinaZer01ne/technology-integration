package com.github.micro.service.gateway.fallback;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务降级
 * @author peach
 * @since 2020/12/4 11:05
 */
@RestController
public class FallbackController {

    @RequestMapping("/fallback")
    public String test() {
        return "服务降级";
    }
}