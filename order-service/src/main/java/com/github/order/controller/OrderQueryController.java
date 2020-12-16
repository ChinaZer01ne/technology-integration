package com.github.order.controller;

import com.github.common.core.response.ServerResponse;
import com.github.internal.api.order.dto.OrderDTO;
import com.github.order.entity.Order;
import com.github.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 获取订单信息
     * @param orderId :
     * @return com.github.common.core.response.ServerResponse<com.github.order.entity.Order>
     */
    @RequestMapping("/get")
    public ServerResponse<Order> get(@RequestParam("orderId") Long orderId){
        return ServerResponse.success(orderService.getById(orderId));
    }

}