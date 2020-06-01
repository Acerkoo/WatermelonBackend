package cn.watermelon.watermelonbackend.service.impl;

import cn.watermelon.watermelonbackend.entity.Comment;
import cn.watermelon.watermelonbackend.handler.BaseHandler;
import cn.watermelon.watermelonbackend.mapper.CommentMapper;
import cn.watermelon.watermelonbackend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import java.util.ArrayList;
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
        BaseHandler.sendMessageToAllUsers(new TextMessage("comment"));
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
    public List<Comment> getComments() {
        List<Comment> list = commentMapper.getCommentList();
        List<Comment> result = new ArrayList<>();
        for (Comment comment: list) {
            if (comment.getFollowId() == null) {
                commentMapper.addCommentBrowseNum(comment.getCommentId());
                comment.setBrowseNum(comment.getBrowseNum() + 1);
                result.add(comment);
            }
        }
        return result;
    }

    @Override
    public List<Comment> getCommentByUserId(int userId) {
        List<Comment> list = commentMapper.getCommentListByUserId(userId);
        List<Comment> result = new ArrayList<>();
        for (Comment comment: list) {
            if (comment.getFollowId() == null) {
                commentMapper.addCommentBrowseNum(comment.getCommentId());
                comment.setBrowseNum(comment.getBrowseNum() + 1);
                result.add(comment);
            }
        }
        return result;
    }

    @Override
    public boolean addCommentAdmire(int commentId, int userId) {
        try {
            commentMapper.addAdmireHistory(userId, commentId, new Date());
            commentMapper.addAdmireNum(commentId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean removeCommentAdmire(int commentId, int userId) {
        try {
            commentMapper.subAdmireNum(commentId);
            commentMapper.removeAdmireHistory(userId, commentId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Comment> getUserAdmireHistory(int userId) {
        List<Integer> list = commentMapper.getUserAdmireHistory(userId);
        List<Comment> result = new ArrayList<>();
        for (Integer commentId: list) {
            Comment comment = commentMapper.getCommentByCommentId(commentId);
            commentMapper.addCommentBrowseNum(comment.getCommentId());
            comment.setBrowseNum(comment.getBrowseNum() + 1);
            result.add(comment);
        }
        return result;
    }

    @Override
    public List<Comment> getFollowComment(int commentId) {
        List<Comment> result = commentMapper.getFollowComment(commentId);
        for (Comment comment: result) {
            commentMapper.addCommentBrowseNum(comment.getCommentId());
            comment.setBrowseNum(comment.getBrowseNum() + 1);
        }
        return commentMapper.getFollowComment(commentId);
    }

    @Override
    public Boolean checkAdmire(int commentId, int passerId) {
        if (commentMapper.checkAdmire(commentId, passerId) != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Comment> getUserHistory(int userId) {
        List<Comment> comments = commentMapper.getCommentListByUserId(userId);
        List<Comment> result = new ArrayList<>();
        for (Comment comment: comments) {
            result.add(comment);
            commentMapper.addCommentBrowseNum(comment.getCommentId());
            comment.setBrowseNum(comment.getBrowseNum() + 1);
            if (comment.getFollowId() != null) {
                result.addAll(commentMapper.getCommentByFollowId(comment.getFollowId()));
            }
        }
        return result;
    }

}
