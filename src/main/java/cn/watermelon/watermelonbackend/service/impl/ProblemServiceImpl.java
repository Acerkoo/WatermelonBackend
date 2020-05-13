package cn.watermelon.watermelonbackend.service.impl;

import cn.watermelon.watermelonbackend.entity.Problem;
import cn.watermelon.watermelonbackend.mapper.ProblemMapper;
import cn.watermelon.watermelonbackend.mapper.TagMapper;
import cn.watermelon.watermelonbackend.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Problem> findAll() {
        return problemMapper.getAllProblems();
    }

    @Override
    public List<Problem> findProblemByName(String name) {
        return problemMapper.findProblemByName(name);
    }

    public List<Problem> getFindProblemByName(int id) {
        return problemMapper.findProblemById(id);
    }

    public List<Problem> findProblemById(int id) {
        return problemMapper.findProblemById(id);
    }

    @Override
    public void addProblemTag(int problemId, String tag) {
        if (tagMapper.checkProblemTag(problemId, tag) != 0) {
            tagMapper.addProblemTag(problemId, tag);
        } else {
            tagMapper.insertProblemTag(problemId, tag);
        }
    }

    @Override
    public List<Problem> getProblemByTag(String tag) {
        List<Integer> problemIds = problemMapper.getProblemByTag(tag);
        List<Problem> problemList = new ArrayList<>();
        for (Integer problemId: problemIds) {
            problemList.add(problemMapper.getProblemById(problemId));
        }
        return problemList;
    }

    public int addProblem(Problem problem) {
        return problemMapper.addProblem(problem);
    }

}
