package cn.watermelon.watermelonbackend.mapper;

import cn.watermelon.watermelonbackend.entity.Problem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProblemMapper {

    List<Problem> findAll();

    List<Problem> findProblemById(int id);

    List<Problem> findProblemByName(String name);

    int addProblem(Problem problem);

    @Select({"SELECT *",
            "FROM `problem`",
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
    List<Problem> getAllProblems();

}