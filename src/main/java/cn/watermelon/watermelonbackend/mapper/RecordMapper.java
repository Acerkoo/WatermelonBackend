package cn.watermelon.watermelonbackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RecordMapper {

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
}
