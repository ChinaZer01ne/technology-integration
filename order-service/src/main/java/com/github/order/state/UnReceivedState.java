package com.github.order.state;

import com.github.order.enums.OrderStateEnum;

/**
 * 待收货状态
 * @author Zer01ne
 * @since 2020/11/29 1:11
 */
public class UnReceivedState implements State {
    @Override
    public void doAction(Context context) {
        context.setState(new FinishState());
    }

    @Override
    public OrderStateEnum getState() {
        return OrderStateEnum.UN_RECEIVED;
    }
}
