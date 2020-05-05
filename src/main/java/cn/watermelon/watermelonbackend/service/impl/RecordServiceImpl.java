package cn.watermelon.watermelonbackend.service.impl;

import cn.watermelon.watermelonbackend.mapper.RecordMapper;
import cn.watermelon.watermelonbackend.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public int getAcRecordByProblem(int userId, int problemId) {
        return recordMapper.getAcRecordByProblem(userId, problemId);
    }

    @Override
    public int getRecordByProblem(int userId, int problemId) {
        return recordMapper.getRecordByProblem(userId, problemId);
    }

    @Override
    public int getAcRecordByContest(int userId, int problemId, int contestId) {
        return recordMapper.getAcRecordByContest(userId, problemId, contestId);
    }

    @Override
    public int getRecordByContest(int userId, int problemId, int contestId) {
        return recordMapper.getRecordByContest(userId, problemId, contestId);
    }

}
