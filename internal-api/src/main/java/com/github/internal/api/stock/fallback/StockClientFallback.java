package com.github.internal.api.stock.fallback;

import com.github.internal.api.order.dto.OrderDTO;
import com.github.internal.api.stock.StockClient;

/**
 * @author peach
 * @since 2020/12/11 14:26
 */
public class StockClientFallback implements StockClient {
    @Override
    public boolean lock(OrderDTO order) {
        return false;
    }

    @Override
    public boolean deduct(OrderDTO orderDTO) {
        return false;
    }
}