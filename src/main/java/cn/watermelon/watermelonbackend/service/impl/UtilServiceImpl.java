package cn.watermelon.watermelonbackend.service.impl;

import cn.watermelon.watermelonbackend.mapper.UtilMapper;
import cn.watermelon.watermelonbackend.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilServiceImpl implements UtilService {

    @Autowired
    private UtilMapper utilMapper;

    @Override
    public int getAcRecordByProblem(int userId, int problemId) {
        return utilMapper.getAcRecordByProblem(userId, problemId);
    }

    @Override
    public int getRecordByProblem(int userId, int problemId) {
        return utilMapper.getRecordByProblem(userId, problemId);
    }

    @Override
    public int getAcRecordByContest(int userId, int problemId, int contestId) {
        return utilMapper.getAcRecordByContest(userId, problemId, contestId);
    }

    @Override
    public int getRecordByContest(int userId, int problemId, int contestId) {
        return utilMapper.getRecordByContest(userId, problemId, contestId);
    }

    @Override
    public List<String> getProblemTag(int problemId) {
        return utilMapper.getProblemTag(problemId);
    }

    @Override
    public List<String> getContestTag(int contestId) {
        return utilMapper.getContestTag(contestId);
    }

    @Override
    public int getAcNum(int problemId) {
        return utilMapper.getProblemAcNum(problemId);
    }

    @Override
    public int getSubNum(int problemId) {
        return utilMapper.getProblemSubNum(problemId);
    }

}
