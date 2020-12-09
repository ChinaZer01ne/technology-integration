package com.github.micro.service.gateway.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.common.core.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 用来解决认证过的用户访问无权限资源时的异常
 * @author Zer01ne
 * @since 2020/11/28 16:10
 */
@Slf4j
@Component
public class CustomizedAccessDeniedHandler implements ServerAccessDeniedHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, AccessDeniedException e) {
        log.info(e.getMessage());
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.writeAndFlushWith(subscriber -> {
            subscriber.onError(e);
        });

        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                return bufferFactory.wrap(objectMapper.writeValueAsBytes(ServerResponse.fail("无权限")));
            } catch (JsonProcessingException ex) {
                log.error("Error writing response", ex);
                return bufferFactory.wrap(new byte[0]);
            }
        }));
        //return Mono.create(MonoSink::success);
    }
}
