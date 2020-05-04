package cn.watermelon.watermelonbackend.mapper;

import cn.watermelon.watermelonbackend.entity.Problem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ContestWithProblemMapper {

    List<Problem> findProblemByContest(int contestId);

    @Select({"SELECT `problem_id`",
            "FROM `contest_with_problem`",
            "WHERE `is_delete` = false",
            "AND `contest_id` = #{contestId}",
    })
    List<Integer> getProblemIdByContestId(int contestId);

    @Select({"SELECT *",
            "FROM `problem`",
            "WHERE `problem_id` = #{problemId}",
    })
    @Results(value = {
            @Result(property = "keyId", column = "problem_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "input", column = "input"),
            @Result(property = "output", column = "output"),
            @Result(property = "contestId", column = "contest_id"),
            @Result(property = "isSpj", column = "spj"),
            @Result(property = "visible", column = "visible"),
            @Result(property = "tmLimit", column = "tm_limit"),
            @Result(property = "memLimit", column = "mem_limit"),
            @Result(property = "sampleInput", column = "sample_input"),
            @Result(property = "sampleOutput", column = "sample_output"),
            @Result(property = "contestId", column = "contest_id"),
    })
    Problem getProblemById(int problemId);

    @Insert({"INSERT INTO `contest_with_problem`",
            "(`problem_id`, `contest_id`)",
            "VALUES",
            "(#{problemId}, #{contestId})",
    })
    void addProblem2Contest(int problemId, int contestId);

    @Update({"UPDATE `contest_with_problem`",
            "SET `is_delete` = true",
            "WHERE `problem_id` = #{problemId} AND `contest_id` = #{contestId}",
    })
    void deleteProblemFromContest(int problemId, int contestId);

    @Update({"UPDATE `contest_with_problem`",
            "SET `is_delete` = true",
            "WHERE `contest_id` = #{contestId}",
    })
    void deleteContest(int contestId);
}
