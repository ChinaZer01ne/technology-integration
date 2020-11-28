package com.github.order.state;
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
}
