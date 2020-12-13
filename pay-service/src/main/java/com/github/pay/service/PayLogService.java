package com.github.pay.service;

import com.github.pay.entity.PayLog;

/**
 * @author Zer01ne
 * @since 2020/11/28 23:55
 */
public interface PayLogService {

    /**
     * 保存支付日志
     * @param payLog :
     * @return boolean
     */
    boolean save(PayLog payLog);
}
