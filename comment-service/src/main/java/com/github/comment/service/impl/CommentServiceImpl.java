package com.github.comment.service.impl;

import com.github.comment.entity.Comment;
import com.github.comment.entity.dto.CommentDTO;
import com.github.comment.manager.CommentManager;
import com.github.comment.param.CommentPageQueryParam;
import com.github.comment.repository.CommentRepository;
import com.github.comment.service.CommentService;
import com.github.common.core.response.PageResult;

import com.github.common.core.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author peach
 * @since 2021/12/2 0:24
 */
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentManager commentManager;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public PageResult<Comment> list(CommentPageQueryParam commentPageQueryParam) {
        Integer pageNum = commentPageQueryParam.getPageNum();
        if (pageNum < 1) {
            pageNum = 1;
        }
        Integer pageSize = commentPageQueryParam.getPageSize();
        String topicId = commentPageQueryParam.getTopicId();
        if (StringUtils.isBlank(topicId)) {
            return PageResult.warp(new ArrayList<>(), pageNum, pageSize, 0L);
        }

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.Direction.DESC,  "createTime", "likeCount");

        Comment comment = new Comment();
        comment.setTopicId(commentPageQueryParam.getTopicId());
        //创建匹配器，组装查询条件
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("topicId", ExampleMatcher.GenericPropertyMatchers.exact());
        //创建实例
        Example<Comment> example = Example.of(comment, matcher);

        Page<Comment> res = commentRepository.findAll(example, pageable);
        return PageResult.warp(res.getContent(), pageNum, pageSize, res.getTotalElements());
    }

    @Override
    public boolean save(CommentDTO comment) {
        if (!checkParams(comment)) {
            log.info("comment 含有为空的字段");
            return false;
        }

        //组装待保存的Comment
        Comment commentDO = new Comment();
        commentDO.setTopicId(comment.getTopicId());
        commentDO.setFromUserId(comment.getUserId());
        commentDO.setComment(comment.getComment());
        commentDO.setLikeCount(0);
        LocalDateTime nowDate = LocalDateTime.now();
        commentDO.setCreateTime(nowDate);
        commentDO.setUpdateTime(nowDate);
        commentDO.setIsDelete(Boolean.FALSE);
        log.info("保存的留言对象：{}", JsonUtils.toJsonString(comment));
        return commentManager.save(commentDO);

    }

    /**
     * 检查留言不为空
     * @param comment :
     * @return boolean
     */
    private boolean checkParams(CommentDTO comment) {
        if (comment == null) {
            log.info("comment 为 null");
            return false;
        }

        Integer userId = comment.getUserId();
        if (userId == null) {
            log.info("留言中的 userId 为 null");
            return false;
        }

        String topicId = comment.getTopicId();
        if (StringUtils.isBlank(topicId)) {
            log.info("留言中的 courseId 为 null");
            return false;
        }

        String commentContent = comment.getComment();
        if (StringUtils.isBlank(commentContent)) {
            log.info("留言中的内容 为 null");
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteCourseComment(String commentId, Integer userId) {
        commentRepository.delete(new Comment());
        return false;
    }

    @Override
    public List<CommentDTO> getUserById(Integer userId) {
//        commentRepository.findOne();
        return null;
    }

    @Override
    public CommentDTO favorite(Integer userId, String commentId) {
        commentRepository.save(new Comment());
        return null;
    }

    @Override
    public boolean cancelFavorite(Integer userId, String commentId) {
        commentRepository.save(new Comment());
        return false;
    }
}
