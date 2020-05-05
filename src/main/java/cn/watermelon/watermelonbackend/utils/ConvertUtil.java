package cn.watermelon.watermelonbackend.utils;

import cn.watermelon.watermelonbackend.dto.CommentResponseDTO;
import cn.watermelon.watermelonbackend.dto.ProblemDTO;
import cn.watermelon.watermelonbackend.entity.Comment;
import cn.watermelon.watermelonbackend.entity.Problem;
import cn.watermelon.watermelonbackend.enumeration.ProblemStatus;
import cn.watermelon.watermelonbackend.service.RecordService;

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

    public static List<ProblemDTO> problemDTOList(List<Problem> list, int userId, RecordService recordService, Integer contestId) {
        List<ProblemDTO> result = new ArrayList<>();
        if (list != null) {
            for (Problem problem : list) {
                ProblemDTO problemDTO = new ProblemDTO(problem);
                int status = 0;
                int problemId = problem.getKeyId();
                if (contestId == null) {
                    if (recordService.getAcRecordByProblem(userId, problemId) > 0) {
                        status = 1;
                    } else if (recordService.getRecordByProblem(userId, problemId) > 0) {
                        status = 2;
                    }
                } else {
                    if (recordService.getAcRecordByContest(userId, problemId, contestId) > 0) {
                        status = 1;
                    } else if (recordService.getRecordByContest(userId, problemId, contestId) > 0) {
                        status = 2;
                    }
                }
                problemDTO.setStatus(ProblemStatus.getProblemStatus(status));
                result.add(problemDTO);
            }
        }
        return result;
    }

}
