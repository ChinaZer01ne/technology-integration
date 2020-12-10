package com.github.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webflux.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.github.sentinel.handler.FluxBlockRequestHandler;
import com.github.sentinel.handler.ServletBlockExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author peach
 * @since 2020/12/7 14:04
 */
@Configuration
public class SentinelAutoConfiguration {
    /**
     * 针对webflux的自定义降级策略
     * @return com.alibaba.csp.sentinel.adapter.spring.webflux.callback.BlockRequestHandler
     */
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
    @Bean
    public BlockRequestHandler blockRequestHandler() {
        return new FluxBlockRequestHandler();
    }

    /**
     * 针对mvc的自定义降级策略
     * @return com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler
     */
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    @Bean
    public BlockExceptionHandler blockExceptionHandler() {
        return new ServletBlockExceptionHandler();
    }
}