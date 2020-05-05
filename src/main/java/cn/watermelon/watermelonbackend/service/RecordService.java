package cn.watermelon.watermelonbackend.service;

public interface RecordService {

    int getAcRecordByProblem(int userId, int problemId);

    int getRecordByProblem(int userId, int problemId);

    int getAcRecordByContest(int userId, int problemId, int contestId);

    int getRecordByContest(int userId, int problemId, int contestId);

}
