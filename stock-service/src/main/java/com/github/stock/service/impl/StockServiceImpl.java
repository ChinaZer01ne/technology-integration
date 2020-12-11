package com.github.stock.service.impl;

import com.github.internal.api.order.dto.OrderDTO;
import com.github.stock.service.StockService;
import org.springframework.stereotype.Service;

/**
 * @author peach
 * @since 2020/12/11 16:41
 */
@Service
public class StockServiceImpl implements StockService {
    @Override
    public boolean lock(OrderDTO order) {
        return false;
    }

    @Override
    public boolean deduct(OrderDTO order) {
        return false;
    }
}