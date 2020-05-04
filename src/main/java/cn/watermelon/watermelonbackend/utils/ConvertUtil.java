package cn.watermelon.watermelonbackend.utils;

import cn.watermelon.watermelonbackend.dto.CommentResponseDTO;
import cn.watermelon.watermelonbackend.dto.ProblemDTO;
import cn.watermelon.watermelonbackend.entity.Comment;
import cn.watermelon.watermelonbackend.entity.Problem;

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

        public static List<ProblemDTO> problemDTOList(List<Problem> list) {
            List<ProblemDTO> result = new ArrayList<>();
            if (list != null) {
                for (Problem problem: list) {
                    result.add(new ProblemDTO(problem));
                }
            }
            return result;
        }

}
