package cn.watermelon.watermelonbackend.utils;

import cn.watermelon.watermelonbackend.dto.CommentResponseDTO;
import cn.watermelon.watermelonbackend.dto.ProblemDTO;
import cn.watermelon.watermelonbackend.entity.Comment;
import cn.watermelon.watermelonbackend.entity.Problem;
import cn.watermelon.watermelonbackend.enumeration.ProblemStatus;
import cn.watermelon.watermelonbackend.service.CommentService;
import cn.watermelon.watermelonbackend.service.ImageService;
import cn.watermelon.watermelonbackend.service.UtilService;

import java.util.ArrayList;
import java.util.List;

public class ConvertUtil {

    public static List<CommentResponseDTO> prs2Subs(List<Comment> list, CommentService commentService,
                                                    Integer passerId, ImageService imageService) {
        List<CommentResponseDTO> result = new ArrayList<>();
        if (list != null) {
            for (Comment comment : list) {
                CommentResponseDTO commentResponseDTO = new CommentResponseDTO(comment);
                if (passerId != null) {
                    commentResponseDTO.setAdmired(commentService.checkAdmire(comment.getCommentId(), passerId));
                } else {
                    commentResponseDTO.setAdmired(false);
                }
                commentResponseDTO.setFollowNum(commentService.getFollowComment(comment.getCommentId()).size());
                commentResponseDTO.setUserImageUrl(imageService.getImageUrl(comment.getUserId(), 0));
                result.add(commentResponseDTO);
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
                problemDTO.setAcNum(utilService.getAcNum(problemId));
                problemDTO.setCeNum(utilService.getProblemStatus(problemId, 2));
                problemDTO.setPeNum(utilService.getProblemStatus(problemId, 3));
                problemDTO.setReNum(utilService.getProblemStatus(problemId, 4));
                problemDTO.setTleNum(utilService.getProblemStatus(problemId, 5));
                problemDTO.setMleNum(utilService.getProblemStatus(problemId, 6));
                problemDTO.setWaNum(utilService.getProblemStatus(problemId, 7));
                problemDTO.setSubNum(utilService.getSubNum(problemId));
                List<String> tags = utilService.getProblemTag(problemId);
                problemDTO.setProblemTags(tags);
                result.add(problemDTO);
            }
        }
        return result;
    }

}
