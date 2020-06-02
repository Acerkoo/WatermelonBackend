package cn.watermelon.watermelonbackend.controller;

import cn.watermelon.watermelonbackend.dto.CommentResponseDTO;
import cn.watermelon.watermelonbackend.entity.Comment;
import cn.watermelon.watermelonbackend.service.CommentService;
import cn.watermelon.watermelonbackend.service.ImageService;
import cn.watermelon.watermelonbackend.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    void createComment(Integer userId, String username, String title, String content, Integer followId) {
        Comment comment = new Comment();
        comment.setUsername(username);
        comment.setUserId(userId);
        comment.setTitle(title);
        comment.setContent(content);
        comment.setFollowId(followId);

        commentService.insertComment(comment);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    void deleteComment(Integer commentId) {
        commentService.deleteComment(commentId);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    List<CommentResponseDTO> getCommentsByUserId(Integer userId, Integer passerId) {
        if (userId == null) {
            return ConvertUtil.prs2Subs(commentService.getComments(), commentService, passerId, imageService);
        }
        return ConvertUtil.prs2Subs(commentService.getCommentByUserId(userId), commentService, passerId, imageService);
    }

    @RequestMapping(value = "/admire", method = RequestMethod.POST)
    Boolean addCommentAdmire(int commentId, int userId) {
        return commentService.addCommentAdmire(commentId, userId);
    }

    @RequestMapping(value = "/admire", method = RequestMethod.DELETE)
    Boolean removeCommentAdmire(int commentId, int userId) {
        return commentService.removeCommentAdmire(commentId, userId);
    }

    @RequestMapping(value = "/admire", method = RequestMethod.GET)
    List<CommentResponseDTO> getUserAdmireHistory(int userId, Integer passerId) {
        return ConvertUtil.prs2Subs(commentService.getUserAdmireHistory(userId), commentService, passerId, imageService);
    }

    @RequestMapping(value = "/follow", method = RequestMethod.GET)
    List<CommentResponseDTO> getFollowComment(int commentId, Integer passerId) {
        return ConvertUtil.prs2Subs(commentService.getFollowComment(commentId), commentService, passerId, imageService);
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    List<CommentResponseDTO> getUserHistory(int userId, Integer passerId) {
        return ConvertUtil.prs2Subs(commentService.getUserHistory(userId), commentService, passerId, imageService);
    }

}
