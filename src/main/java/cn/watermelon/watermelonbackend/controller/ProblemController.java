package cn.watermelon.watermelonbackend.controller;

import cn.watermelon.watermelonbackend.dto.ProblemDTO;
import cn.watermelon.watermelonbackend.entity.Problem;
import cn.watermelon.watermelonbackend.job.ProblemJob;
import cn.watermelon.watermelonbackend.service.ProblemService;
import cn.watermelon.watermelonbackend.service.UtilService;
import cn.watermelon.watermelonbackend.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private UtilService utilService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ProblemJob problemJob;

    @RequestMapping(value = "/problem/all", method = RequestMethod.GET)
    public List<ProblemDTO> getAll(int userId) {
        String param2 = "problemDTO" + userId;
        List<Problem> problems = problemJob.getProblemInfo();
        if (redisTemplate.opsForValue().get(param2) == null) {
            redisTemplate.opsForValue().set(param2, ConvertUtil.problemDTOList(problems, userId, utilService, null), 1, TimeUnit.MINUTES);
        }
        List<ProblemDTO> result = (List<ProblemDTO>) redisTemplate.opsForValue().get(param2);
//        List<ProblemDTO> result = ConvertUtil.problemDTOList(problemService.findAll(), userId, utilService, null);
        return result;
    }

    @RequestMapping(value = "/problem/name", method = RequestMethod.GET)
    public List<ProblemDTO> getProblemByName(String name, int userId) {
        return ConvertUtil.problemDTOList(problemService.findProblemByName(name), userId,
                utilService, null);
    }

    @RequestMapping(value = "/problem/id", method = RequestMethod.GET)
    public List<ProblemDTO> getProblemById(int id, int userId) {
        return ConvertUtil.problemDTOList(problemService.findProblemById(id), userId,
                utilService, null);
    }

    @RequestMapping(value = "/problem/tag", method = RequestMethod.POST)
    public void addProblemTag(int problemId, String tag) {
        String param = "problem_tag_" + problemId;
        problemService.addProblemTag(problemId, tag);
        redisTemplate.opsForValue().set(param, utilService.getProblemTag(problemId), 10, TimeUnit.MINUTES);
    }

    @RequestMapping(value = "/problem/tag", method = RequestMethod.GET)
    public List<String> getProblemTag(int problemId) {
        String param = "problem_tag_" + problemId;
        List<String> result = (List<String>) redisTemplate.opsForValue().get(param);
        if (result == null) {
            result = utilService.getProblemTag(problemId);
        }
        return result;
    }

    @RequestMapping(value = "/problem/sort", method = RequestMethod.GET)
    public List<ProblemDTO> getProblemByTag(int userId, String tag) {
        return ConvertUtil.problemDTOList(problemService.getProblemByTag(tag), userId, utilService, null);
    }

}
