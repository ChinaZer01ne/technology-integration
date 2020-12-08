package com.github.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webflux.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

/**
 * @author peach
 * @since 2020/12/7 14:04
 */
@Configuration
public class SentinelConfig {

    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
    @Bean
    public BlockRequestHandler blockRequestHandler() {
        return new BlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange exchange,
                                                      Throwable t) {
                return ServerResponse.status(444).contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(JSONObject.toJSONString(com.github.common.core.ServerResponse.fail(t.getMessage()))));
            }
        };
    }

    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    @Bean
    public BlockExceptionHandler blockExceptionHandler() {
        return new BlockExceptionHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
                //logger.error("sentinel 降级 资源名称{}", e.getRule().getResource(), e);
                response.setContentType(MediaType.APPLICATION_JSON.getType());
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.getWriter().print(JSONObject.toJSONString(com.github.common.core.ServerResponse.fail(e.getMessage())));
            }
        };
    }
}