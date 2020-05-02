package cn.watermelon.watermelonbackend.service.impl;

import cn.watermelon.watermelonbackend.entity.Problem;
import cn.watermelon.watermelonbackend.mapper.ProblemMapper;
import cn.watermelon.watermelonbackend.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService
{
    @Autowired
    private ProblemMapper problemMapper;
    @Override
    public List<Problem> findAll()
    {
        return problemMapper.findAll();
    }
    @Override
    public List<Problem> findProblemByName(String name)
    {
        return problemMapper.findProblemByName(name);
    }
<<<<<<< HEAD
    public List<Problem> getFindProblemByName(int id)
    {
        return problemMapper.findProblemById(id);
    }

=======
    public List<Problem> findProblemById(int id)
    {
        return problemMapper.findProblemById(id);
    }
    public int addProblem(Problem problem)
    {
        return problemMapper.addProblem(problem);
    }
>>>>>>> dbeed8fb9adc7adadc42aef706c68dffd03163d5
}
