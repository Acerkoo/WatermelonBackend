package cn.watermelon.watermelonbackend.service.impl;

import cn.watermelon.watermelonbackend.entity.Contest;
import cn.watermelon.watermelonbackend.entity.Problem;
import cn.watermelon.watermelonbackend.mapper.ContestMapper;
import cn.watermelon.watermelonbackend.mapper.ContestWithProblemMapper;
import cn.watermelon.watermelonbackend.service.ContestService;
import cn.watermelon.watermelonbackend.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContestServiceImpl implements ContestService {

    @Autowired
    ContestMapper contestMapper;

    @Autowired
    private ContestWithProblemMapper contestWithProblemMapper;

    @Override
    public List<Contest> getAllContest() {
       return contestMapper.findAllContest();
    }

    public Contest findContestById(int id) {
        return contestMapper.findContestById(id);
    }

    public List<Problem> findContestProblem(int contestId) {
        List<Integer> result = contestWithProblemMapper.getProblemIdByContestId(contestId);
        List<Problem> problemList = new ArrayList<>();
        for (Integer problemId: result) {
            problemList.add(contestWithProblemMapper.getProblemById(problemId));
        }
        return problemList;
    }

    @Override
    public Contest getContestByContestId(int contestId) {
        return contestMapper.getContestByContestId(contestId);
    }

    @Override
    public Contest createContest(String title, String description, String hostname, Date startTime, Date endTime) {
        Date date = new Date();
        contestMapper.createContest(title, description, hostname, startTime, endTime, date);
        return contestMapper.getContestByTime();
    }

    @Override
    public void deleteContest(int contestId) {
        contestMapper.deleteContest(contestId);
        contestWithProblemMapper.deleteContest(contestId);
    }

    @Override
    public void updateContest(Contest contest) {
        contestMapper.updateContest(contest);
    }

    @Override
    public void addProblem2Contest(int problemId, int contestId) {
        contestWithProblemMapper.addProblem2Contest(problemId, contestId);
    }

    @Override
    public void deleteProblemFromContest(int problemId, int contestId) {
        contestWithProblemMapper.deleteProblemFromContest(problemId, contestId);
    }


}
