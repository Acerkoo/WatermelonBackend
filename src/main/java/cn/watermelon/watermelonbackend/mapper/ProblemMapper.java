package cn.watermelon.watermelonbackend.mapper;

import cn.watermelon.watermelonbackend.entity.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProblemMapper {

    List<Problem> findAll();

    List<Problem> findProblemById(int id);

    List<Problem> findProblemByName(String name);

    int addProblem(Problem problem);
}