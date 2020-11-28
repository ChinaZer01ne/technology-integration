package com.github.order.service.impl;

import com.github.order.entity.Order;
import com.github.order.enums.OrderStateEnum;
import com.github.order.service.OrderService;
import com.github.order.state.Context;
import com.github.order.state.UnPaidState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zer01ne
 * @since 2020/11/29 1:24
 */
@Service
public class OrderServiceImpl implements OrderService {

    //@Autowired
    //private OrderMapper orderMapper;
    @Override
    public void create(Order order) {

    }

    @Override
    public void paid(Order order) {
        UnPaidState unPaidState = new UnPaidState();
        Context context = new Context(unPaidState);
        context.doAction();

        //orderMapper.updateState(OrderStateEnum.UNPAID, OrderStateEnum.UN_SEND);
    }
}
