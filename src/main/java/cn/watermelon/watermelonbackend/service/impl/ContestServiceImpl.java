package cn.watermelon.watermelonbackend.service.impl;

import cn.watermelon.watermelonbackend.entity.Contest;
import cn.watermelon.watermelonbackend.entity.Problem;
import cn.watermelon.watermelonbackend.mapper.ContestMapper;
import cn.watermelon.watermelonbackend.mapper.ContestWithProblemMapper;
import cn.watermelon.watermelonbackend.mapper.TagMapper;
import cn.watermelon.watermelonbackend.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContestServiceImpl implements ContestService {

    @Autowired
    private ContestMapper contestMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ContestWithProblemMapper contestWithProblemMapper;

    @Override
    public List<Contest> getAllContest() {
        List<Contest> tmpContests = contestMapper.getAllContest();
        List<Contest> nowContest = new ArrayList<>();
        List<Contest> result = new ArrayList<>();
        Date date = new Date();
        for (Contest contest: tmpContests) {
            if (date.compareTo(contest.getEndTime()) > 0) {
                result.add(contest);
            } else {
                nowContest.add(contest);
            }
        }
        Collections.sort(nowContest);
        Collections.reverse(result);
//        result.addAll(nowContest);
        nowContest.addAll(result);
        for (Contest contest: nowContest) {
            System.out.println(contest);
        }
        return nowContest;
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

    public void addContestTag(int contestId, String tag) {
        if (tagMapper.checkContestTag(contestId, tag) != 0) {
            tagMapper.addContestTag(contestId, tag);
        } else {
            tagMapper.insertContestTag(contestId, tag);
        }
    }

    @Override
    public List<Contest> getContestByTag(String tag) {
        List<Integer> contestIds = contestMapper.getContestByTag(tag);
        List<Contest> contests = new ArrayList<>();
        for (Integer contestId: contestIds) {
            contests.add(contestMapper.getContestByContestId(contestId));
        }
        return contests;
    }

}
