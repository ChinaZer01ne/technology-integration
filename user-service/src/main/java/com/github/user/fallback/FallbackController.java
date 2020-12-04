package com.github.user.fallback;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试降级端点的跳转
 * @author peach
 * @since 2020/12/4 11:05
 */
@RestController
public class FallbackController {

    @RequestMapping("/fallback")
    public String test() {
        return "fallback";
    }
}