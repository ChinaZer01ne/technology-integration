package com.github.order.state;
/**
 * 已完成状态
 * @author Zer01ne
 * @since 2020/11/29 1:11
 */
public class FinishState implements State {
    @Override
    public void doAction(Context context) {
        context.setState(null);
    }
}
