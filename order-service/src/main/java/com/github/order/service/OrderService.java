package com.github.order.service;

import com.github.order.entity.Order;

/**
 * @author Zer01ne
 * @since 2020/11/29 1:24
 */
public interface OrderService {

    /**
     * 创建订单
     * @param order : 订单信息
     */
    void create(Order order);

    /**
     * 订单支付完成，修改订单状态
     * @param order : 订单信息
     */
    void paid(Order order);
}
