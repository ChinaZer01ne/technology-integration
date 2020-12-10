package com.github.sentinel.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.github.common.core.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author peach
 * @since 2020/12/9 16:18
 */
//@Slf4j
public class ServletBlockExceptionHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        System.out.println("sentinel 降级 资源名称{}" + e);
        response.setContentType(MediaType.APPLICATION_JSON.getType());
        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        response.getWriter().print(JSONObject.toJSONString(ServerResponse.fail(e.getMessage())));
    }

}