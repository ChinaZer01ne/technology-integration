package com.github.comment.repository;

import com.github.comment.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author peach
 * @since 2021/12/4 17:24
 */
public interface CommentRepository extends MongoRepository<Comment, Long> {

    List<Comment> getProductCommentList();
}
