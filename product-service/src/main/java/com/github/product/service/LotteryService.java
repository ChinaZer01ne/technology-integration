package com.github.product.service;

import com.github.product.entity.Lottery;

/**
 * 抽奖
 * @author Zer01ne
 * @since 2020/11/21 10:51
 */
public interface LotteryService {
    /**
     * 可重复抽奖
     */
    Lottery repeatable();

    /**
     * 不可重复抽奖
     */
    Lottery unrepeatable();
}
