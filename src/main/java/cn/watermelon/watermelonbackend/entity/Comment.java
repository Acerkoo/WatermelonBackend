package cn.watermelon.watermelonbackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Comment {

    int commentId;

    int userId;

    String title;

    String content;

    Integer followId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    Date createTime;

    String username;

}
