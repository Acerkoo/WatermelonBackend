package cn.watermelon.watermelonbackend.service;

import cn.watermelon.watermelonbackend.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    void insertComment(Comment comment);

    void deleteComment(int commentId);

    List<Comment> getCommentByProblemId(int problemId);

    List<Comment> getCommentByUserId(int userId);

}
