package com.github.micro.service.gateway.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.common.core.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 用来解决匿名用户访问无权限资源时的异常
 * @author Zer01ne 
 * @since 2020/11/28 16:10
 */
@Slf4j
@Component
public class CustomizedAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Void> commence(ServerWebExchange serverWebExchange, AuthenticationException e) {
        log.info("用户匿名访问! {}", e.getMessage());
        ServerHttpResponse response = serverWebExchange.getResponse();

        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                return bufferFactory.wrap(objectMapper.writeValueAsBytes(ServerResponse.fail("匿名访问，无权限")));
            } catch (JsonProcessingException ex) {
                log.error("Error writing response", ex);
                return bufferFactory.wrap(new byte[0]);
            }
        }));
    }
}