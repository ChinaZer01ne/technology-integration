package com.github.product.service.impl;


import com.github.product.constants.ProductConstants;
import com.github.product.entity.vo.GiveLikeVO;
import com.github.product.service.GiveLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Zer01ne
 * @since 2020/11/21 13:37
 */
@Slf4j
@Service
public class GiveLikeServiceImpl implements GiveLikeService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public GiveLikeVO get(Long productId, Long userId) {
        String key = ProductConstants.GIVE_LIKE + productId;
        Boolean member = false;
        Long size = 0L;
        try {
            member = redisTemplate.opsForSet().isMember(key, userId);
            size = redisTemplate.opsForSet().size(key);
        }catch (Exception e) {
            log.info("redis异常:{}",e.getMessage());

        }
        if (member) {
            // 返回大小
            // 返回用户是否点赞
            GiveLikeVO giveLike = new GiveLikeVO();
            giveLike.setProductId(productId);
            giveLike.setGiveLikeCount(size);
            giveLike.setIsClick(1);
            return giveLike;
        }else {
            GiveLikeVO giveLike = new GiveLikeVO();
            giveLike.setProductId(productId);
            giveLike.setGiveLikeCount(size);
            giveLike.setIsClick(0);
            return giveLike;
        }
    }

    @Override
    public GiveLikeVO click(Long id, Long userId) {
        String key = ProductConstants.GIVE_LIKE + id;
        try {
            Boolean member = redisTemplate.opsForSet().isMember(key, userId);
            if (!member) {
                redisTemplate.opsForSet().add(key,userId);
            }
            Long size = redisTemplate.opsForSet().size(key);
            GiveLikeVO giveLike = new GiveLikeVO();
            giveLike.setProductId(id);
            giveLike.setGiveLikeCount(size);
            giveLike.setIsClick(1);
            return giveLike;
        }catch (Exception e) {
            log.info("redis异常:{}",e.getMessage());
        }
        return null;
    }
}
