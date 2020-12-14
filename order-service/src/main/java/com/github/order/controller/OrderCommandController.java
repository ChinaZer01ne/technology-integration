package com.github.order.controller;

import com.github.order.command.impl.OrderCreateCommand;
import com.github.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String create(@RequestBody OrderCreateCommand orderCreateCommand){
        orderService.create(orderCreateCommand);
        return "ok";
    }
}