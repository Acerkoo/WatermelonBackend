package cn.watermelon.watermelonbackend.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {

    @Insert({"INSERT INTO `problem_with_tag`",
            "(`problem_id`, `tag`, `num`)",
            "VALUES",
            "(#{problemId}, #{tag}, 1)",
    })
    void insertProblemTag(int problemId, String tag);

    @Update({"UPDATE `problem_with_tag`",
            "SET `num` = `num` + 1",
            "WHERE `problem_id` = #{problemId} AND `tag` = #{tag}",
    })
    void addProblemTag(int problemId, String tag);

    @Select({"SELECT COUNT(*)",
            "FROM `problem_with_tag`",
            "WHERE `problem_id` = #{problemId} AND `tag` = #{tag}",
    })
    int checkProblemTag(int problemId, String tag);

    @Select({"SELECT `tag`",
            "FROM `problem_with_tag`",
            "WHERE `problem_id` = #{problemId}",
    })
    List<String> getProblemTag(int problemId);

    @Insert({"INSERT INTO `contest_with_tag`",
            "(`contest_id`, `tag`, `num`)",
            "VALUES",
            "(#{contestId}, #{tag}, 1)",
    })
    void insertContestTag(int contestId, String tag);

    @Update({"UPDATE `contest_with_tag`",
            "SET `num` = `num` + 1",
            "WHERE `contest_id` = #{contestId} AND `tag` = #{tag}",
    })
    void addContestTag(int contestId, String tag);

    @Select({"SELECT COUNT(*)",
            "FROM `contest_with_tag`",
            "WHERE `contest_id` = #{contestId} AND `tag` = #{tag}",
    })
    int checkContestTag(int contestId, String tag);

    @Select({"SELECT `tag`",
            "FROM `problem_with_tag`",
            "WHERE `problem_id` = #{problemId}",
    })
    List<String> getContestTag(int contestId);

}
