package com.github.product.service.impl;

import com.github.product.entity.input.CartRequest;
import com.github.product.entity.vo.CartProductVO;
import com.github.product.entity.vo.CartVO;
import com.github.product.service.ShopCartService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author peach
 * @since 2020/11/17 13:46
 */
@Service
public class ShopCartServiceImpl implements ShopCartService {

    private static final String CART_KEY = "cart:user:%d";

    private final RedisTemplate<String, Object> redisTemplate;

    public ShopCartServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public CartVO addCart(CartRequest cartRequest) {
        /**
         * 添加购物车：
         * 1、判断用户是否登录
         *      如果登录了，缓存购物车，发消息，同步数据到数据库
         *      如果没登录，缓存购物车
         * */
        // TODO 用户可能未登录，用户id可能为null，并且这个用户id不应该从前端传过来，而是由鉴权中心带过来
        boolean isLogin = true;
        if (isLogin) {
            addCartForOnline(cartRequest);
        }else {
            addCartForOffline(cartRequest);
        }
        //return get(cartRequest.getCartId());
        redisTemplate.opsForHash().put(String.format("cart:user:%d",cartRequest.getUserId()),cartRequest.getProductId().toString(),cartRequest.getProductNum());
        Set<Object> keys = redisTemplate.opsForHash().keys(String.format("cart:user:%d", cartRequest.getUserId()));

        CartVO cartVO = new CartVO();
        List<CartProductVO> cartProductList = new ArrayList<>();
        for (Object productId : keys) {
            CartProductVO cartProductVO = new CartProductVO();
            cartProductVO.setProductId(Long.valueOf((String)productId));
            Object productNum = redisTemplate.opsForHash().get(String.format("cart:user:%d", cartRequest.getUserId()), productId);
            cartProductVO.setProductNum((Integer) productNum);
            cartProductVO.setUserId(cartRequest.getUserId());
            cartProductList.add(cartProductVO);
        }
        cartVO.setCartProductList(cartProductList);
        return cartVO;
    }

    /**
     * 未登录状态添加购物车
     * @param cartRequest : 添加购物车请求参数
     * @return void
     */
    private void addCartForOffline(CartRequest cartRequest) {

    }

    /**
     * 登录用户添加购物车
     * @param cartRequest : 添加购物车请求参数
     * @return void
     */
    private void addCartForOnline(CartRequest cartRequest) {
        String cartKey = String.format(CART_KEY, cartRequest.getUserId());
        Boolean hasKey = redisTemplate.hasKey(cartKey);
        // 这行代码有两个功能：
        // 1、如果用户原来有购物车信息存在，那么直接修改购物车商品信息；
        // 2、如果用户原来没有购物车信息存在，那么创建购物车，并保存商品信息。
        redisTemplate.opsForHash().put(cartKey, cartRequest.getProductId().toString(), cartRequest.getProductNum());
        if (!hasKey) {
            // 如果redis中没有这个用户的购物车，需要给新建的购物车设置过期时间
            redisTemplate.expire(cartKey,3, TimeUnit.MINUTES);
        }
        // TODO 发消息同步数据库
    }

    @Override
    public CartVO deleteCart(CartRequest cartRequest) {
        String cartKey = String.format(CART_KEY, cartRequest.getUserId());
        redisTemplate.opsForHash().delete(cartKey, cartRequest.getProductId().toString());
        // TODO 发消息同步数据库
        return get(cartRequest.getCartId());
    }

    @Override
    public CartVO mergeCart(Long cartId) {
        return null;
    }

    @Override
    public CartVO get(Long cartId) {
        Long userId = 10000L;
        String cartKey = String.format(CART_KEY, userId);
        Long size = redisTemplate.opsForHash().size(cartKey);
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(cartKey);
        List<CartProductVO> cartProductList = new ArrayList<>();
        for (Map.Entry<Object, Object> entry : entries.entrySet()) {
            CartProductVO cartProduct = new CartProductVO();
            cartProduct.setProductId(Long.valueOf((String)entry.getKey()));
            cartProduct.setProductNum((Integer) entry.getValue());
            cartProductList.add(cartProduct);
        }
        CartVO cart = new CartVO();
        cart.setCartProductList(cartProductList);
        cart.setSize(size);
        return cart;
    }
}