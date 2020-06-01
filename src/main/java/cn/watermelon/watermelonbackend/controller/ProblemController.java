package cn.watermelon.watermelonbackend.controller;

import cn.watermelon.watermelonbackend.dto.ProblemDTO;
import cn.watermelon.watermelonbackend.entity.Problem;
import cn.watermelon.watermelonbackend.service.ProblemService;
import cn.watermelon.watermelonbackend.service.UtilService;
import cn.watermelon.watermelonbackend.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @RequestMapping(value = "/problem/all", method = RequestMethod.GET)
    public List<ProblemDTO> getAll(int userId) {
        synchronized (this) {
            if (redisTemplate.opsForValue().get("problem") == null) {
                redisTemplate.opsForValue().set("problem", problemService.findAll(), 300, TimeUnit.SECONDS);
            }
        }
        List<Problem> result = (List<Problem>) redisTemplate.opsForValue().get("problem");
        return ConvertUtil.problemDTOList(result, userId, utilService, null);
    }

    @RequestMapping(value = "/problem/name", method = RequestMethod.GET)
    public List<ProblemDTO> getProblemByName(String name, int userId) {
        String param = "problem_" + name;
        if (redisTemplate.opsForValue().get(param) == null) {
            synchronized (this) {
                if (redisTemplate.opsForValue().get(param) == null) {
                    redisTemplate.opsForValue().set(param, problemService.findProblemByName(name), 120, TimeUnit.SECONDS);
                }
            }
        }
        List<Problem> result = (List<Problem>) redisTemplate.opsForValue().get(param);
        return ConvertUtil.problemDTOList(result, userId, utilService, null);
    }

    @RequestMapping(value = "/problem/id", method = RequestMethod.GET)
    public List<ProblemDTO> getProblemById(int id, int userId) {
        return ConvertUtil.problemDTOList(problemService.findProblemById(id), userId, utilService, null);
    }

    @RequestMapping(value = "/problem/tag", method = RequestMethod.POST)
    public void addProblemTag(int problemId, String tag) {
        problemService.addProblemTag(problemId, tag);
    }

    @RequestMapping(value = "/problem/tag", method = RequestMethod.GET)
    public List<String> getProblemTag(int problemId) {
        String param = "problem_tag_" + problemId;
        if (redisTemplate.opsForValue().get(param) == null) {
            synchronized (this) {
                if (redisTemplate.opsForValue().get(param) == null) {
                    redisTemplate.opsForValue().set(param, utilService.getProblemTag(problemId), 120, TimeUnit.SECONDS);
                }
            }
        }
        List<String> result = (List<String>) redisTemplate.opsForValue().get(param);
        return result;
    }

    @RequestMapping(value = "/problem/sort", method = RequestMethod.GET)
    public List<ProblemDTO> getProblemByTag(int userId, String tag) {
        return ConvertUtil.problemDTOList(problemService.getProblemByTag(tag), userId, utilService, null);
    }

}
