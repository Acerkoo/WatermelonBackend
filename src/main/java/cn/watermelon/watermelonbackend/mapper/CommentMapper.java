package cn.watermelon.watermelonbackend.mapper;

import cn.watermelon.watermelonbackend.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert({"INSERT into `comments`",
            "(`user_id`, `username`, `title`, `content`, `follow_id`, `create_time`, `admire_num`, `is_delete`)",
            "VALUES",
            "(#{userId}, #{username}, #{title}, #{content}, #{followId}, #{createTime}, 0, false)"
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
            "WHERE `is_delete` = false",
            "ORDER BY `create_time` DESC",
    })
    @Results(value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "admireNum", column = "admire_num"),
            @Result(property = "followId", column = "follow_id"),
    })
    List<Comment> getCommentList();

    @Select({"SELECT * FROM `comments`",
            "WHERE `is_delete` = false AND `user_id` = #{userId}",
            "ORDER BY `create_time` DESC",
    })
    @Results(value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "admireNum", column = "admire_num"),
            @Result(property = "followId", column = "follow_id"),
            @Result(property = "commentId", column = "comment_id"),
    })
    List<Comment> getCommentListByUserId(int userId);

    @Select({"SELECT * FROM `comments`",
            "WHERE `is_delete` = false AND `follow_id` = #{commentId}",
            "ORDER BY `create_time` DESC",
    })
    @Results(value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "admireNum", column = "admire_num"),
            @Result(property = "followId", column = "follow_id"),
            @Result(property = "commentId", column = "comment_id"),
    })
    List<Comment> getFollowComment(int commentId);

    @Select({"SELECT * FROM `comments`",
            "WHERE `is_delete` = false AND `follow_id` = #{followId}",
            "ORDER BY `create_time` DESC",
    })
    @Results(value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "admireNum", column = "admire_num"),
            @Result(property = "followId", column = "follow_id"),
            @Result(property = "commentId", column = "comment_id"),
    })
    List<Comment> getCommentByFollowId(int followId);

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

    @Update({"UPDATE `comments`",
            "SET `admire_num` = `admire_num` + 1",
            "WHERE `comment_id` = #{commentId}",
    })
    void addAdmireNum(int commentId);

    @Insert({"INSERT INTO `user_with_comment`",
            "(`user_id`, `comment_id`, `opt_time`)",
            "VALUES",
            "(#{userId}, #{commentId}, #{optTime})",
    })
    void addAdmireHistory(int userId, int commentId, Date optTime);


    @Update({"UPDATE `comments`",
            "SET `admire_num` = `admire_num` - 1",
            "WHERE `comment_id` = #{commentId}",
    })
    void subAdmireNum(int commentId);

    @Delete({"DELETE FROM `user_with_comment`",
            "WHERE `user_id` = #{userId} AND `comment_id` = #{commentId}",
    })
    void removeAdmireHistory(int userId, int commentId);

    @Select({"SELECT `comment_id`",
            "FROM `user_with_comment`",
            "WHERE `user_id` = #{userId}",
            "ORDER BY `opt_time` DESC",
    })
    List<Integer> getUserAdmireHistory(int userId);

    @Select({"SELECT COUNT(*)",
            "FROM `user_with_comment`",
            "WHERE `comment_id` = #{commentId} AND `user_id` = #{passerId}",
    })
    int checkAdmire(int commentId, int passerId);

}
