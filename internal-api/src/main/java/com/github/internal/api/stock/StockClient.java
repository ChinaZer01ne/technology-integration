package com.github.internal.api.stock;

import com.github.internal.api.order.dto.OrderDTO;
import com.github.internal.api.stock.fallback.StockClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author peach
 * @since 2020/12/11 14:25
 */
@FeignClient(name = "stock-service", contextId = "stockClient", fallback = StockClientFallback.class)
public interface StockClient {

    /**
     * 锁定库存
     * @param order : 订单信息
     * @return boolean
     */
    @PostMapping("/internal/stock/get")
    boolean lock(OrderDTO order);

    /**
     * 扣减库存
     * @param order :
     * @return void
     */
    @PostMapping("/internal/stock/deduct")
    boolean deduct(OrderDTO order);
}