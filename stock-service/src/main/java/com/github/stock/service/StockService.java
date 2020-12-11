package com.github.stock.service;

import com.github.internal.api.order.dto.OrderDTO;

/**
 * @author peach
 * @since 2020/12/11 16:40
 */
public interface StockService {
    /**
     * 锁定库存
     * @param order : 订单
     * @return boolean
     */
    boolean lock(OrderDTO order);

    /**
     * 扣减库存
     * @param order : 订单
     * @return boolean
     */
    boolean deduct(OrderDTO order);
}