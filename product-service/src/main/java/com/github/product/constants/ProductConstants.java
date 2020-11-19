package com.github.product.constants;
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
}
