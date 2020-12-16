package com.github.pay.service;

import com.github.pay.entity.dto.PayParamDTO;
import com.github.pay.entity.dto.PayResultDTO;
import com.github.pay.entity.dto.RefundParamDTO;
import com.github.pay.entity.dto.RefundResultDTO;

/**
 * @author Zer01ne
 * @since 2020/11/28 23:55
 */
public interface PayService {

    /**
     * 支付，用于业务处理
     * @param payParam :
     * @return com.github.pay.entity.dto.PayResultDTO
     */
    PayResultDTO pay(PayParamDTO payParam);

    /**
     * 退款，用于业务处理
     * @param refundParamDTO :
     * @return com.github.pay.entity.dto.RefundResultDTO
     */
    RefundResultDTO refund(RefundParamDTO refundParamDTO);

}
