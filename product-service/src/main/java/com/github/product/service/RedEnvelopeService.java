package com.github.product.service;

/**
 * @author peach
 * @since 2020/11/15 19:04
 */
public interface RedEnvelopeService {


    /**
     * 发红包
     * @param amount : 红包金额
     * @param size : 红包个数
     * @param desc : 红包描述
     * @param type : 红包类型
     * @return java.lang.Long 红包id
     */
    Long send(Integer amount, Integer size, String desc, Integer type);

    /**
     * 抢红包
     * @param id : 红包id
     * @return java.lang.String
     */
    String grab(Long id);
}
