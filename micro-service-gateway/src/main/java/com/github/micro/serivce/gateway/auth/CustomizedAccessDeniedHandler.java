package com.github.micro.serivce.gateway.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

/**
 * 用来解决认证过的用户访问无权限资源时的异常
 * @author Zer01ne
 * @since 2020/11/28 16:10
 */
@Slf4j
@Component
public class CustomizedAccessDeniedHandler implements ServerAccessDeniedHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, AccessDeniedException e) {
        log.info(e.getMessage());
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.writeAndFlushWith(subscriber -> {
            subscriber.onError(e);
        });
        return Mono.create(MonoSink::success);
    }
}
