package cn.watermelon.watermelonbackend.dto;

import cn.watermelon.watermelonbackend.entity.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CommentResponseDTO {

    int commentId;

    int userId;

    String username;

    String title;

    String content;

    Integer followId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    Date createTime;

    public CommentResponseDTO(Comment comment) {
        this.commentId = comment.getCommentId();
        this.userId = comment.getUserId();
        this.username = comment.getUsername();
        this.title = comment.getTitle();
        this.content = comment.getContent();
        this.followId = comment.getCommentId();
        this.createTime = comment.getCreateTime();
    }
}
