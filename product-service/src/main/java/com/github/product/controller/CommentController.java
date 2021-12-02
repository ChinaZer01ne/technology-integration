package com.github.product.controller;

import com.github.common.core.response.PageResult;
import com.github.common.core.response.ServerResponse;
import com.github.product.param.CommentPageQueryParam;
import com.github.product.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 评论controller
 *
 * @author peach
 * @since 2021/12/2 0:19
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/list")
    public PageResult<String> list(CommentPageQueryParam commentPageQueryParam) {
        return commentService.list(commentPageQueryParam);
    }
}
