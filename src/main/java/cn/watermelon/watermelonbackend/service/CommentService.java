package cn.watermelon.watermelonbackend.service;

import cn.watermelon.watermelonbackend.entity.Comment;

import java.util.List;

public interface CommentService {

    void insertComment(Comment comment);

    void deleteComment(int commentId);

    List<Comment> getComments();

    List<Comment> getCommentByUserId(int userId);

}
