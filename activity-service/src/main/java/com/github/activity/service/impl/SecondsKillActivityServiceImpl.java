package com.github.activity.service.impl;

import com.github.activity.entity.ActivityProduct;
import com.github.activity.service.SecondsKillActivityService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author peach
 * @since 2021/1/18 14:14
 */
@Service
public class SecondsKillActivityServiceImpl implements SecondsKillActivityService {

    @Override
    public List<ActivityProduct> getActivityProduct(Integer activityType) {
        return null;
    }

    @Override
    public Boolean createOrder(Integer productId) {
        return null;
    }

    @Override
    public Boolean deductStock(Integer productId) {
        return null;
    }
}