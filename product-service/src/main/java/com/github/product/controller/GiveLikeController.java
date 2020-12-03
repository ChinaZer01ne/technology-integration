package com.github.product.controller;

import com.github.common.core.ServerResponse;
import com.github.product.entity.vo.GiveLikeVO;
import com.github.product.service.GiveLikeService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 点赞功能
 * @author Zer01ne
 * @since 2020/11/21 9:17
 */
@RestController
@RequestMapping("/comment/black")
public class GiveLikeController {
    @Autowired
    private GiveLikeService giveLikeService;

    @GetMapping("get")
    public ServerResponse<GiveLikeVO> get(@ApiParam("商品id") @RequestParam("id") Long id) {
        // 获取用户id
        Long userId = 1000L;
        return ServerResponse.success(giveLikeService.get(id, userId));
    }

    @PostMapping("click")
    public ServerResponse<GiveLikeVO> click(@ApiParam("商品id") @RequestParam("id") Long id) {
        Long userId = 1000L;
        return ServerResponse.success(giveLikeService.click(id, userId));
    }
}
