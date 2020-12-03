package com.github.product.controller;

import com.github.common.core.ServerResponse;
import com.github.product.entity.Lottery;
import com.github.product.service.LotteryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 抽奖
 * @author Zer01ne
 * @since 2020/11/21 10:48
 */
@RestController
@RequestMapping("/lottery")
public class LotteryController {

    @Autowired
    private LotteryService lotteryService;

    @ApiOperation("可重复抽奖")
    @RequestMapping("/repeatable")
    public ServerResponse<Lottery> repeatable(){
        return ServerResponse.success(lotteryService.repeatable());
    }

    @ApiOperation("不可重复抽奖")
    @RequestMapping("/unrepeatable")
    public ServerResponse<Lottery> unrepeatable(){
        return ServerResponse.success(lotteryService.unrepeatable());
    }
}
