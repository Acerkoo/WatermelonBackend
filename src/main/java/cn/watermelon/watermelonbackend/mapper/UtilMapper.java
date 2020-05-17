package cn.watermelon.watermelonbackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UtilMapper {

    @Select({"SELECT COUNT(*)",
            "FROM `submissions`",
            "WHERE `is_delete` = false AND `user_id` = #{userId} AND `problem_id` = #{problemId} AND `result` = 1",
    })
    int getAcRecordByProblem(int userId, int problemId);

    @Select({"SELECT COUNT(*)",
            "FROM `submissions`",
            "WHERE `is_delete` = false AND `user_id` = #{userId} AND `problem_id` = #{problemId}",
    })
    int getRecordByProblem(int userId, int problemId);

    @Select({"SELECT COUNT(*)",
            "FROM `submissions`",
            "WHERE `is_delete` = false AND `user_id` = #{userId} AND `problem_id` = #{problemId} AND `contest_id` = #{contestId} AND `result` = 1",
    })
    int getAcRecordByContest(int userId, int problemId, int contestId);

    @Select({"SELECT COUNT(*)",
            "FROM `submissions`",
            "WHERE `is_delete` = false AND `user_id` = #{userId} AND `problem_id` = #{problemId} AND `contest_id` = #{contestId}",
    })
    int getRecordByContest(int userId, int problemId, int contestId);

    @Select({"SELECT `tag`",
            "FROM `problem_with_tag`",
            "WHERE `problem_id` = #{problemId}",
            "LIMIT 0, 5",
    })
    List<String> getProblemTag(int problemId);

    @Select({"SELECT `tag`",
            "FROM `contest_with_tag`",
            "WHERE `contest_id` = #{problemId}",
            "LIMIT 0, 5",
    })
    List<String> getContestTag(int contestId);

    @Select({"SELECT COUNT(*)",
            "FROM `submissions`",
            "WHERE `problem_id` = #{problemId} AND `result` = 1",
    })
    int getProblemAcNum(int problemId);

    @Select({"SELECT COUNT(*)",
            "FROM `submissions`",
            "WHERE `problem_id` = #{problemId}",
    })
    int getProblemSubNum(int problemId);

}
