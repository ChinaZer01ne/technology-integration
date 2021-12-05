package com.github.comment.manager;

import com.github.comment.entity.Comment;
import com.github.comment.entity.dto.CommentDTO;
import com.github.comment.repository.CommentRepository;
import com.github.common.core.utils.EmojiCharacterConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zer01ne
 * @since 2021/12/5 23:06
 */
@Slf4j
@Service
public class CommentManager {

    @Autowired
    private CommentRepository commentRepository;

    /**
     * 保存评论
     * @param comment :
     * @return boolean
     */
    public boolean save(Comment comment){
        if (comment == null) {
            log.error("参数为null");
            return false;
        }
        try {
            comment.setComment(EmojiCharacterConvertUtil.emojiConvertString(comment.getComment()));
            commentRepository.insert(comment);
            return true;
        } catch (Exception e) {
            log.error("保存课程信息异常,courseId = {},lessonId={}", comment.getTopicId(), e);
            return false;
        }
    }
}
