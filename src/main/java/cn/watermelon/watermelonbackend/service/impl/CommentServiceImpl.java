package cn.watermelon.watermelonbackend.service.impl;

import cn.watermelon.watermelonbackend.entity.Comment;
import cn.watermelon.watermelonbackend.mapper.CommentMapper;
import cn.watermelon.watermelonbackend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void insertComment(Comment comment) {
        Date date = new Date();
        comment.setCreateTime(date);
        commentMapper.insertComment(comment);
    }

    @Override
    public void deleteComment(int commentId) {
        Comment comment = commentMapper.getCommentByCommentId(commentId);
        commentMapper.deleteComment(commentId);
        if (comment.getFollowId() == null) {
            commentMapper.deleteCommentByFollowId(commentId);
        } else {
            commentMapper.updateCommentFollow(commentId, comment.getFollowId());
        }
    }

    @Override
    public List<Comment> getCommentByProblemId(int problemId) {
        return commentMapper.getCommentListByProblemId(problemId);
    }

    @Override
    public List<Comment> getCommentByUserId(int userId) {
        return commentMapper.getCommentListByUserId(userId);
    }

}
