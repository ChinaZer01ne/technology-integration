package com.github.order.state;

import com.github.order.enums.OrderStateEnum;

/**
 * 待发货状态
 * @author Zer01ne
 * @since 2020/11/29 1:11
 */
public class UnSendState implements State {
    @Override
    public void doAction(Context context) {
        context.setState(new UnReceivedState());
    }

    @Override
    public OrderStateEnum getState() {
        return OrderStateEnum.UN_SEND;
    }
}
