package cn.watermelon.watermelonbackend.dto;

import cn.watermelon.watermelonbackend.entity.Problem;
import lombok.Data;

@Data
public class ProblemDTO {

    int problemId;
    String title;
    String description;
    String input;
    String output;
    String hint;
    boolean isSpj;
    int contestId;
    boolean visible;
    String tmLimit;
    String sampleInput;
    String sampleOutput;
    String memLimit;

    public ProblemDTO(Problem problem) {
        this.problemId = problem.getKeyId();
        this.title = problem.getTitle();
        this.description = problem.getDescription();
        this.input = problem.getInput();
        this.output = problem.getOutput();
        this.hint = problem.getHint();
        this.isSpj = problem.isSpj();
        this.contestId = problem.getContestId();
        this.visible = problem.isVisible();
        this.tmLimit = problem.getTmLimit();
        this.memLimit = problem.getMemLimit();
        this.sampleInput = problem.getSampleInput();
        this.sampleOutput = problem.getSampleOutput();
    }
}
