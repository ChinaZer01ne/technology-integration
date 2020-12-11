package com.github.order.service;

import com.github.order.command.impl.OrderCreateCommand;
import com.github.order.entity.Order;

/**
 * @author Zer01ne
 * @since 2020/11/29 1:24
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderCreateCommand : 订单信息
     */
    void create(OrderCreateCommand orderCreateCommand);

}
