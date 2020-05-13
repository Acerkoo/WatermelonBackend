package cn.watermelon.watermelonbackend.service;

import cn.watermelon.watermelonbackend.entity.Contest;
import cn.watermelon.watermelonbackend.entity.Problem;

import java.util.Date;
import java.util.List;

public interface ContestService {

    List<Contest> getAllContest();

    List<Problem> findContestProblem(int contestId);

    Contest getContestByContestId(int contestId);

    Contest createContest(String title, String description, String hostname, Date startTime, Date endTime);

    void deleteContest(int contestId);

    void updateContest(Contest contest);

    void addProblem2Contest(int problemId, int contestId);

    void deleteProblemFromContest(int problemId, int contestId);

    void addContestTag(int contestId, String tag);

    List<Contest> getContestByTag(String tag);

}
