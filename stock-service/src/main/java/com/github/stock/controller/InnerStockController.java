package com.github.stock.controller;

import com.github.internal.api.order.dto.OrderDTO;
import com.github.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peach
 * @since 2020/12/11 16:37
 */
@RestController
@RequestMapping("/internal/stock")
public class InnerStockController {

    @Autowired
    private StockService stockService;

    /**
     * 锁定库存
     * @param order : 订单信息
     * @return boolean
     */
    @PostMapping("/lock")
    public boolean lock(@RequestBody OrderDTO order) {
        stockService.lock(order);
        return true;
    }

    /**
     * 扣减库存
     * @param order :
     * @return void
     */
    @PostMapping("/deduct")
    public boolean deduct(OrderDTO order){
        stockService.deduct(order);
        return true;
    }
}