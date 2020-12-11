package com.github.order.state;

import com.github.order.enums.OrderStateEnum;

/**
 * 已取消状态
 * @author Zer01ne
 * @since 2020/11/29 1:11
 */
public class CancelState implements State {
    @Override
    public void doAction(Context context) {
        context.setState(null);
    }

    @Override
    public OrderStateEnum getState() {
        return OrderStateEnum.CANCEL;
    }
}
