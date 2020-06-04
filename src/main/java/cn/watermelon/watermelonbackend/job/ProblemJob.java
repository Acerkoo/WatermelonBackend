package cn.watermelon.watermelonbackend.job;

import cn.watermelon.watermelonbackend.entity.Problem;
import cn.watermelon.watermelonbackend.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ProblemJob {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("Problem")
    private String param;

    private List<Problem> problems;

    public ProblemJob() {
        problems = new ArrayList<>();
    }

    public List<Problem> getProblemInfo() {
        if (problems.size() == 0) {
             problemWork();
        }
        return problems;
    }

    @Scheduled(fixedRate = 1000 * 60 * 30)
    private void problemWork() {
        if (redisTemplate.opsForValue().get(param) == null) {
            redisTemplate.opsForValue().set(param, problemService.findAll(), 30, TimeUnit.MINUTES);
        }
        problems = (List<Problem>) redisTemplate.opsForValue().get(param);
        System.out.println("problems");
    }

}
