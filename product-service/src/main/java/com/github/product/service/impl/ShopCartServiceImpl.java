package com.github.product.service.impl;

import com.github.product.entity.input.CartRequest;
import com.github.product.entity.vo.CartProductVO;
import com.github.product.entity.vo.CartVO;
import com.github.product.service.ShopCartService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author peach
 * @since 2020/11/17 13:46
 */
@Service
public class ShopCartServiceImpl implements ShopCartService {

    private final RedisTemplate<String, Object> redisTemplate;

    public ShopCartServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public CartVO addCart(CartRequest cartRequest) {
        // TODO 用户可能未登录，用户id可能为null，并且这个用户id不应该从前端传过来，而是由鉴权中心带过来
        redisTemplate.opsForHash().put(String.format("cart:user:%d",cartRequest.getUserId()),cartRequest.getProductId(),cartRequest.getProductNum());
        Set<Object> keys = redisTemplate.opsForHash().keys(String.format("cart:user:%d", cartRequest.getUserId()));

        CartVO cartVO = new CartVO();
        List<CartProductVO> cartProductList = new ArrayList<>();
        for (Object productId : keys) {
            CartProductVO cartProductVO = new CartProductVO();
            cartProductVO.setProductId((Long) productId);
            Object productNum = redisTemplate.opsForHash().get(String.format("cart:user:%d", cartRequest.getUserId()), productId);
            cartProductVO.setProductNum((Integer) productNum);
            cartProductVO.setUserId(cartRequest.getUserId());
            cartProductList.add(cartProductVO);
        }
        cartVO.setCartProductList(cartProductList);
        return cartVO;
    }

    @Override
    public CartVO deleteCart(CartRequest cartRequest) {
        return null;
    }

    @Override
    public CartVO mergeCart(Long cartId) {
        return null;
    }
}