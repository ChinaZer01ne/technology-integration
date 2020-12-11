package com.github.pay.controller;

import com.github.common.core.response.ServerResponse;
import com.github.pay.entity.dto.PayParamDTO;
import com.github.pay.entity.dto.PayResultDTO;
import com.github.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peach
 * @since 2020/12/11 15:57
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;

    /**
     * 支付接口
     * @param payParam : 支付参数
     * @return com.github.common.core.response.ServerResponse<com.github.pay.entity.dto.PayResultDTO>
     */
    @PostMapping("/do")
    public ServerResponse<PayResultDTO> pay(@RequestBody PayParamDTO payParam) {
        return ServerResponse.success(payService.pay(payParam));
    }
}