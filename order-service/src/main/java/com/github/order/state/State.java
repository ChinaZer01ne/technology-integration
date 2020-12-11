package com.github.order.state;

import com.github.order.enums.OrderStateEnum;

/**
 * 状态机的状态接口
 * @author Zer01ne
 * @since 2020/11/29 0:46
 */
public interface State {

    /**
     * 执行的动作
     * @param context : 状态机上下文
     */
    void doAction(Context context);

    /**
     * 获取订单装填
     * @return com.github.order.enums.OrderStateEnum
     */
    OrderStateEnum getState();
}
