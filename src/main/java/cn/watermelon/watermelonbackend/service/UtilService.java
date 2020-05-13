package cn.watermelon.watermelonbackend.service;

import java.util.List;

public interface UtilService {

    int getAcRecordByProblem(int userId, int problemId);

    int getRecordByProblem(int userId, int problemId);

    int getAcRecordByContest(int userId, int problemId, int contestId);

    int getRecordByContest(int userId, int problemId, int contestId);

    List<String> getProblemTag(int problemId);

    List<String> getContestTag(int contestId);
}