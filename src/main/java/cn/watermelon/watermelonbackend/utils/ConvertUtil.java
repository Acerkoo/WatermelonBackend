package cn.watermelon.watermelonbackend.utils;

import cn.watermelon.watermelonbackend.dto.CommentResponseDTO;
import cn.watermelon.watermelonbackend.dto.ProblemDTO;
import cn.watermelon.watermelonbackend.entity.Comment;
import cn.watermelon.watermelonbackend.entity.Problem;
import cn.watermelon.watermelonbackend.enumeration.ProblemStatus;
import cn.watermelon.watermelonbackend.service.UtilService;

import java.util.ArrayList;
import java.util.List;

public class ConvertUtil {

    public static List<CommentResponseDTO> prs2Subs(List<Comment> list) {
        List<CommentResponseDTO> result = new ArrayList<>();
        if (list != null) {
            for (Comment comment : list) {
                result.add(new CommentResponseDTO(comment));
            }
        }
        return result;
    }

    public static List<ProblemDTO> problemDTOList(List<Problem> list, int userId, UtilService utilService, Integer contestId) {
        List<ProblemDTO> result = new ArrayList<>();
        int rankId = 0;
        if (list != null) {
            for (Problem problem : list) {
                ProblemDTO problemDTO = new ProblemDTO(problem);
                int status = 0;
                int problemId = problem.getKeyId();
                if (contestId == null) {
                    if (utilService.getAcRecordByProblem(userId, problemId) > 0) {
                        status = 1;
                    } else if (utilService.getRecordByProblem(userId, problemId) > 0) {
                        status = 2;
                    }
                } else {
                    if (utilService.getAcRecordByContest(userId, problemId, contestId) > 0) {
                        status = 1;
                    } else if (utilService.getRecordByContest(userId, problemId, contestId) > 0) {
                        status = 2;
                    }
                }
                rankId += 1;
                problemDTO.setStatus(ProblemStatus.getProblemStatus(status));
                problemDTO.setRankId(rankId);
                List<String> tags = utilService.getProblemTag(problemId);
                problemDTO.setProblemTags(tags);
                result.add(problemDTO);
            }
        }
        return result;
    }

}
