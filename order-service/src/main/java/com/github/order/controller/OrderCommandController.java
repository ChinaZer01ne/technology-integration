package com.github.order.controller;

import com.github.common.core.response.ServerResponse;
import com.github.order.command.impl.OrderCreateCommand;
import com.github.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 写订单接口
 * @author peach
 * @since 2020/12/10 17:26
 */
@RestController
@RequestMapping("/order")
public class OrderCommandController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     * @return java.lang.String
     */
    @PostMapping("/create")
    public ServerResponse<Void> create(@RequestBody OrderCreateCommand orderCreateCommand){
        orderService.create(orderCreateCommand);
        return ServerResponse.success();
    }
}