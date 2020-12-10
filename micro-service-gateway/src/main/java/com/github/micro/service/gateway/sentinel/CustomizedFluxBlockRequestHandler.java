package com.github.micro.service.gateway.sentinel;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

/**
 * 自定义熔断后的返回结果
 * @author peach
 * @since 2020/12/10 17:03
 */
@Slf4j
@Component
public class CustomizedFluxBlockRequestHandler implements BlockRequestHandler {

    @Override
    public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable t) {
        log.error("sentinel 降级", t);
        return ServerResponse.status(444).contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(JSONObject.toJSONString(com.github.common.core.response.ServerResponse.fail(t.getMessage()))));
    }

}