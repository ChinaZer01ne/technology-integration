package com.github.order.state;

import com.github.order.enums.OrderStateEnum;

/**
 * 待支付状态
 * @author Zer01ne
 * @since 2020/11/29 1:11
 */
public class UnPaidState implements State {
    @Override
    public void doAction(Context context) {
        context.setState(new UnSendState());
    }

    @Override
    public OrderStateEnum getState() {
        return OrderStateEnum.UNPAID;
    }
}
