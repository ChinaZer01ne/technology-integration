package com.github.pay.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Zer01ne
 * @since 2020/12/13 16:56
 */
@Data
public class PayLog implements Serializable {

    /**
     * id
     */
    private Long id;
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 支付编号
     */
    private Long payId;
    /**
     * 支付时间
     */
    private LocalDateTime payTime;
    /**
     * 支付方式
     */
    private Integer payType;
    /**
     * 支付状态
     */
    private Integer payState;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 业务编号
     */
    private Long bizNo;
    /**
     * 请求操作类型(1.支付,2.退款,3.查询支付,4.查询退款,5.撤销交易)
     */
    private Integer optionType;
    /**
     * 金额
     */
    private Integer amount;
    /**
     * 响应结果状态（枚举：1,失败;2,成功;3,支付中,4.代码异常）
     */
    private Object responseStatus;
    /**
     * 响应内容
     */
    private String responseResult;
    /**
     * 响应时间
     */
    private Date responseTime;
}
