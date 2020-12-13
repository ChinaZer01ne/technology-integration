package com.github.pay.service.impl;

import com.github.pay.entity.PayLog;
import com.github.pay.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zer01ne
 * @since 2020/12/13 17:00
 */
@Service
public class PayLogServiceImpl implements PayLogService {

    //@Autowired
    //private PayLogMapper payLogMapper;

    @Override
    public boolean save(PayLog payLog) {
        //return payLogMapper.insert(payLog);
        return true;
    }
}
