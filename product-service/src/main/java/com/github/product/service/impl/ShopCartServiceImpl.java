package com.github.product.service.impl;

import com.github.product.constants.ProductConstants;
import com.github.product.entity.input.CartRequest;
import com.github.product.entity.vo.CartProductVO;
import com.github.product.entity.vo.CartVO;
import com.github.product.service.ShopCartService;
import com.github.product.utils.IdGenerator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author peach
 * @since 2020/11/17 13:46
 */
@Service
public class ShopCartServiceImpl implements ShopCartService {

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final RedisTemplate<String, Object> redisTemplate;
    private final IdGenerator idGenerator;

    public ShopCartServiceImpl(RedisTemplate<String, Object> redisTemplate, HttpServletRequest request, HttpServletResponse response, IdGenerator idGenerator) {
        this.redisTemplate = redisTemplate;
        this.request = request;
        this.response = response;
        this.idGenerator = idGenerator;
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
        // 1、获取cardId
        Long cardId = getCardIdFromCookie();
        // 2、根据cardId查看redis中是否有对应的购物车信息
        Boolean hasKey = redisTemplate.hasKey(String.format(ProductConstants.COOKIE_CART_KEY, cardId));
        redisTemplate.opsForHash().put(String.format(ProductConstants.COOKIE_CART_KEY, cardId),cartRequest.getProductId(),cartRequest.getProductNum());
        if (!hasKey) {
            redisTemplate.expire(String.format(ProductConstants.COOKIE_CART_KEY, cardId),90,TimeUnit.DAYS);
        }
        //  如果有，直接添加购物车商品
        //  如果没有，新建购物车，添加商品
    }

    /**
     * 从cookie中获取cartId，如果没有，则生成一个，并回写cookie
     * @return java.lang.Long
     */
    private Long getCardIdFromCookie() {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Objects.equals(cookie.getName(),"cardId")) {
                    return Long.valueOf(cookie.getValue());
                }
            }
        }
        Long generateId = idGenerator.generateId();
        Cookie cookie = new Cookie("cartId", String.valueOf(generateId));
        response.addCookie(cookie);
        return generateId;
    }

    /**
     * 登录用户添加购物车
     * @param cartRequest : 添加购物车请求参数
     * @return void
     */
    private void addCartForOnline(CartRequest cartRequest) {
        String cartKey = String.format(ProductConstants.CART_KEY, cartRequest.getUserId());
        Boolean hasKey = redisTemplate.hasKey(cartKey);
        // 这行代码有两个功能：
        // 1、如果用户原来有购物车信息存在，那么直接修改购物车商品信息；
        // 2、如果用户原来没有购物车信息存在，那么创建购物车，并保存商品信息。
        redisTemplate.opsForHash().put(cartKey, cartRequest.getProductId().toString(), cartRequest.getProductNum());
        if (!hasKey) {
            // 如果redis中没有这个用户的购物车，需要给新建的购物车设置过期时间
            redisTemplate.expire(cartKey,90, TimeUnit.DAYS);
        }
        // TODO 发消息同步数据库
    }

    @Override
    public CartVO deleteCart(CartRequest cartRequest) {
        String cartKey = String.format(ProductConstants.CART_KEY, cartRequest.getUserId());
        redisTemplate.opsForHash().delete(cartKey, cartRequest.getProductId().toString());
        // TODO 发消息同步数据库
        return get(cartRequest.getCartId());
    }

    @Override
    public CartVO mergeCart(Long cartId) {
        // 1、取cookie中的购物车
        Long cardId = getCardIdFromCookie();
        String cartKey = String.format(ProductConstants.COOKIE_CART_KEY, cardId);
        //Boolean hasKey = redisTemplate.hasKey(COOKIE_CART_KEY);

        // cookie中购物车信息
        Map<Object, Object> cookieCart = redisTemplate.opsForHash().entries(cartKey);

        // 2、取redis中的购物车
        // 登录用户的购物车信息
        Long userId = 1L;
        Map<Object, Object> userCart = redisTemplate.opsForHash().entries(String.format(ProductConstants.CART_KEY, userId));
        // 3、合并购物车
        userCart.putAll(cookieCart);
        redisTemplate.opsForHash().putAll(String.format(ProductConstants.CART_KEY, userId), userCart);
        // 4、使Cookie中的购物车失效
        Cookie cookie = new Cookie("cardId","");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        // TODO 5、发送消息，同部数据
        return null;
    }

    @Override
    public CartVO get(Long cartId) {
        Long userId = 10000L;
        String cartKey = String.format(ProductConstants.CART_KEY, userId);
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