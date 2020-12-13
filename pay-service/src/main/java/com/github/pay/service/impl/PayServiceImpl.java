package com.github.pay.service.impl;

import com.github.pay.entity.dto.PayParamDTO;
import com.github.pay.entity.dto.PayResultDTO;
import com.github.pay.entity.dto.RefundParamDTO;
import com.github.pay.entity.dto.RefundResultDTO;
import com.github.pay.enums.PaymentMethodEnum;
import com.github.pay.handler.TradeStrategy;
import com.github.pay.handler.entity.PayParam;
import com.github.pay.handler.entity.PayResult;
import com.github.pay.handler.entity.RefundParam;
import com.github.pay.handler.entity.RefundResult;
import com.github.pay.handler.factory.TradeStrategyFactory;
import com.github.pay.service.PayService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zer01ne
 * @since 2020/11/29 0:01
 */
@Service
public class PayServiceImpl implements PayService {


    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public PayResultDTO pay(PayParamDTO payParam) {
        // TODO 可否使用代理模式？
        // 调用第三方接口之前的处理
        // 。。。。
        TradeStrategy tradeStrategy = TradeStrategyFactory.generateStrategy(PaymentMethodEnum.get(payParam.getPaymentMethod()));
        // 转换参数实体
        PayParam param = new PayParam();
        BeanUtils.copyProperties(payParam, param);
        PayResult payResult = tradeStrategy.pay(param);
        // 调用第三方接口之后的处理
        // 。。。
        // TODO 发消息通知订单，修改状态
        rocketMQTemplate.sendMessageInTransaction("OrderPaid", null, payResult);
        return new PayResultDTO();
    }

    @Override
    public RefundResultDTO refund(RefundParamDTO refundParam) {
        // 调用第三方接口之前的处理
        // 。。。。
        TradeStrategy tradeStrategy = TradeStrategyFactory.generateStrategy(PaymentMethodEnum.get(refundParam.getPaymentMethod()));
        // 转换参数实体
        RefundParam param = new RefundParam();
        BeanUtils.copyProperties(refundParam, param);
        RefundResult refundResult = tradeStrategy.refund(param);
        // 调用第三方接口之后的处理
        // 。。。
        return new RefundResultDTO();
    }
}
