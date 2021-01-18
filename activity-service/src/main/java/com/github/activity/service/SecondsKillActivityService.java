package com.github.activity.service;

import com.github.activity.entity.ActivityProduct;

import java.util.List;

/**
 * @author peach
 * @since 2021/1/18 14:13
 */
public interface SecondsKillActivityService {

    /**
     * 获取所有活动商品
     * @param activityType 活动类型
     * @return java.util.List<com.github.activity.entity.ActivityProduct>
     */
    List<ActivityProduct> getActivityProduct(Integer activityType);

    /**
     * 下单
     * @param productId :
     * @return java.util.Boolean
     */
    Boolean createOrder(Integer productId);

    /**
     * 下单
     * @param productId :
     * @return java.util.List<com.github.activity.entity.ActivityProduct>
     */
    Boolean deductStock(Integer productId);
}