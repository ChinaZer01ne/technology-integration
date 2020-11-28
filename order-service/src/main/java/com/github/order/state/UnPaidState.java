package com.github.order.state;
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
}
