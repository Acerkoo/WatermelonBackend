package cn.watermelon.watermelonbackend.mapper;

import cn.watermelon.watermelonbackend.entity.Contest;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface ContestMapper {

    List<Contest> findAllContest();

    Contest findContestById(int id);

    @Select({"SELECT * ",
            "FROM `contest`",
            "WHERE `is_delete` = false AND `contest_id` = #{contestId}",
    })
    @Results(value = {
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
    })
    Contest getContestByContestId(int contestId);

    @Insert({"INSERT INTO `contest`",
            "(`title`, `description`, `hostname`, `start_time`, `end_time`, `create_time`)",
            "VALUES ",
            "(#{title}, #{description}, #{hostname}, #{startTime}, #{endTime}, #{date})",
    })
    void createContest(String title, String description, String hostname, Date startTime, Date endTime, Date date);

    @Select({"SELECT *",
            "FROM `contest`",
            "WHERE `is_delete` = false",
            "ORDER BY `create_time` DESC",
            "LIMIT 0, 1",
    })
    @Results(value = {
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
    })
    Contest getContestByTime();

    @Update({"UPDATE `contest`",
            "SET `is_delete` = true",
            "WHERE `contest_id` = #{contestId}",
    })
    void deleteContest(int contestId);

    @Update({"UPDATE `contest`",
            "SET `title` = #{title}, `description` = #{description}, `start_time` = #{startTime}, `end_time` = #{endTime}, `hostname` = #{hostname}",
            "WHERE `contest_id` = #{contestId}",
    })
    void updateContest(Contest contest);

    @Select({"SELECT `contest_id`",
            "FROM `contest_with_tag`",
            "WHERE  `tag` = #{tag}",
    })
    List<Integer> getContestByTag(String tag);

}
