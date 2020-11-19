package com.github.product.controller;

import com.github.common.ServerResponse;
import com.github.product.service.RedEnvelopeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peach
 * @since 2020/11/19 13:50
 */
@RestController
@RequestMapping("/red/envelope")
public class RedEnvelopeController {

    @Autowired
    private RedEnvelopeService redEnvelopeService;

    @ApiOperation("发红包")
    @PostMapping("/send")
    public ServerResponse<Long> send(@ApiParam("金额") @RequestParam("amount") Integer amount,
                                     @ApiParam("个数") @RequestParam("size") Integer size,
                                     @ApiParam("描述") @RequestParam("desc") String desc,
                                     @ApiParam("红包类型") @RequestParam("type") Integer type){

        return ServerResponse.success(redEnvelopeService.send(amount, size, desc, type));
    }

    @ApiOperation("发红包")
    @PostMapping("/grab")
    public ServerResponse<String> send(@ApiParam("红包id") @RequestParam("id") Long id){
        return ServerResponse.success(redEnvelopeService.grab(id));
    }
}