package com.github.order.state;
/**
 * 状态机上下文：定义客户端所感兴趣的接口，并且保留一个具体状态类的实例。这个具体状态类的实例给出此环境对象的现有状态。
 * @author Zer01ne
 * @since 2020/11/29 1:04
 */
public class Context {

    private State state;

    public Context(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void doAction() {
        state.doAction(this);
    }
}
