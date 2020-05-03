package cn.watermelon.watermelonbackend.mapper;

import cn.watermelon.watermelonbackend.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert({"INSERT into `comments`",
            "(`user_id`, `problem_id`, `title`, `content`, `follow_id`, `create_time`, `is_delete`)",
            "VALUES",
            "(#{userId}, #{problemId}, #{title}, #{content}, #{followId}, #{createTime}, false)"
    })
    void insertComment(Comment comment);

    @Select({"SELECT `comment_id`",
            "FROM `comments",
            "WHERE `is_delete` = false",
            "ORDER BY `create_time` DESC",
            "LIMIT 0,1"
    })
    int getCommentId();

    @Select({"UPDATE `comments`",
            "SET `is_delete` = true",
            "WHERE `comment_id` = #{commentId}"
    })
    void deleteComment(int commentId);

    @Select({"UPDATE `comments`",
            "SET `is_delete` = true",
            "WHERE `follow_id` = #{followId}"
    })
    void deleteCommentByFollowId(int followId);

    @Select({"SELECT * FROM `comments`",
            "WHERE `is_delete` = false AND `problem_id` = #{problemId}",
            "ORDER BY `create_time` DESC",
    })
    @Results(value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
    })
    List<Comment> getCommentListByProblemId(int problemId);

    @Select({"SELECT * FROM `comments`",
            "WHERE `is_delete` = false AND `user_id` = #{userId}",
            "ORDER BY `create_time` DESC",
    })
    @Results(value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
    })
    List<Comment> getCommentListByUserId(int userId);

    @Update({"UPDATE `comments` SET",
            "`follow_id` = #{newFollowId}",
            "WHERE `follow_id` = #{lastFollowId}",
    })
    void updateCommentFollow(int lastFollowId, int newFollowId);

    @Select({"SELECT *",
            "FROM `comments`",
            "WHERE `comment_id` = #{commentId}",
    })
    Comment getCommentByCommentId(int commentId);

}
