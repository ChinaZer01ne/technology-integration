package com.github.order.state;

import com.github.order.enums.OrderStateEnum;

/**
 * 预创建状态
 * @author Zer01ne
 * @since 2020/11/29 1:11
 */
public class PrepareState implements State {
    @Override
    public void doAction(Context context) {
        context.setState(new UnPaidState());
    }

    @Override
    public OrderStateEnum getState() {
        return OrderStateEnum.PRE;
    }

    public State create() {
        return new UnPaidState();
    }
}
