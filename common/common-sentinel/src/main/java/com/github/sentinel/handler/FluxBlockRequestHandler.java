package com.github.sentinel.handler;

import com.alibaba.csp.sentinel.adapter.spring.webflux.callback.BlockRequestHandler;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

/**
 * @author peach
 * @since 2020/12/9 16:18
 */
//@Slf4j
public class FluxBlockRequestHandler implements BlockRequestHandler {

    @Override
    public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable t) {
        //log.error("sentinel 降级", t);
        System.out.println("sentinel 降级" + t);
        return ServerResponse.status(444).contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(JSONObject.toJSONString(com.github.common.core.response.ServerResponse.fail(t.getMessage()))));
    }

}