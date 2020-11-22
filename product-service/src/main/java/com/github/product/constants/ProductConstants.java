package com.github.product.constants;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Zer01ne
 * @since 2020/11/18 20:51
 */
public class ProductConstants {
    /**
     * 登录用户购物车key
     */
    public static final String CART_KEY = "cart:user:%d";
    /**
     * 未登录用户购物车key
     */
    public static final String COOKIE_CART_KEY = "cart:%d";
    /**
     * 特价商品列表key（聚划算，拍拍岛）
     */
    public static final String JHS = "jhs";
    /**
     * 特价商品列表key（聚划算，拍拍岛）
     * 使用二级缓存解决缓存击穿的问题
     */
    public static final String JHS_A = "jhs_a";
    public static final String JHS_B = "jhs_b";
    /**
     * 一级缓存PV map
     */
    public static final Map<Long, Map<Long, Long>> PV_MAP = new ConcurrentHashMap<>();
    /**
     * 一级缓存PV key
     */
    public static final String PV_LIST_CACHE = "pv：list";
    /**
     * 商品pv key
     */
    public static final String PRODUCT_PV = "product:%d";
    /**
     * 评论黑名单集合
     */
    public static final String COMMENT_BLACKLIST = "comment:blacklist";
    /**
     * 可重复抽奖
     */
    public static final String LOTTERY_REPEATABLE = "lottery:repeatable";
    /**
     * 不可重复抽奖
     */
    public static final String LOTTERY_UNREPEATABLE = "lottery:unrepeatable";

    /**
     * 点赞key
     */
    public static final String GIVE_LIKE = "give:like";
    /**
     * topN小时key
     */
    public static final String HOUR_KEY = "topN:hour";
    /**
     * topN天key
     */
    public static final String DAY_KEY = "topN:day";
    /**
     * topN周key
     */
    public static final String WEEK_KEY = "topN:week";
    /**
     * 月topN天key
     */
    public static final String MONTH_KEY = "topN:month";

}
