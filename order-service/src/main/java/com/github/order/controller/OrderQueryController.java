package com.github.order.controller;

import com.github.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 读订单接口
 * @author peach
 * @since 2020/12/10 17:26
 */
@RestController
@RequestMapping("/order")
public class OrderQueryController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/get")
    public String get(){
        return "ok";
    }

}