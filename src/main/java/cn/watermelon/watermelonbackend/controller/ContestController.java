package cn.watermelon.watermelonbackend.controller;

import cn.watermelon.watermelonbackend.dto.ProblemDTO;
import cn.watermelon.watermelonbackend.entity.Contest;
import cn.watermelon.watermelonbackend.enumeration.ContestTag;
import cn.watermelon.watermelonbackend.service.ContestService;
import cn.watermelon.watermelonbackend.service.UtilService;
import cn.watermelon.watermelonbackend.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class ContestController {

    @Autowired
    private ContestService contestService;

    @Autowired
    private UtilService utilService;

    @RequestMapping(value = "/contest/all", method = RequestMethod.GET)
    public List<Contest> getAllContest() {
        List<Contest> result = contestService.getAllContest();
        return result;
    }

    @RequestMapping(value = "/contest/problem", method = RequestMethod.GET)
    public List<ProblemDTO> getProblem(int contestId, int userId) {
        return ConvertUtil.problemDTOList(contestService.findContestProblem(contestId), userId, utilService, contestId);
    }

    @RequestMapping(value = "/contest/id", method = RequestMethod.GET)
    public Contest getContest(int contestId) {
        return contestService.getContestByContestId(contestId);
    }

    @RequestMapping(value = "/contest/create", method = RequestMethod.POST)
    public Contest createContest(String title, String description, String hostname, Date startTime, Date endTime) {
        Contest contest = contestService.createContest(title, description, hostname, startTime, endTime);
        return contest;
    }

    @RequestMapping(value = "/contest/delete", method = RequestMethod.DELETE)
    public void deleteContest(int contestId) {
        contestService.deleteContest(contestId);
    }

    @RequestMapping(value = "/contest/update", method = RequestMethod.PUT)
    public void updateContest(int contestId, String title, String description, String hostname, Date startTime, Date endTime) {
        Contest contest = new Contest();
        contest.setContestId(contestId);
        contest.setDescription(description);
        contest.setTitle(title);
        contest.setHostname(hostname);
        contest.setStartTime(startTime);
        contest.setEndTime(endTime);
        contestService.updateContest(contest);
    }

    @RequestMapping(value = "/contest/add", method = RequestMethod.POST)
    public void addProblem2Contest(int problemId, int contestId) {
        contestService.addProblem2Contest(problemId, contestId);
    }

    @RequestMapping(value = "/contest/sub", method = RequestMethod.DELETE)
    public void deleteProblemFromContest(int problemId, int contestId) {
        contestService.deleteProblemFromContest(problemId, contestId);
    }

    @RequestMapping(value = "contest/tag", method = RequestMethod.POST)
    public void addContestTag(int contestId, String tag) {
        contestService.addContestTag(contestId, tag);
    }

    @RequestMapping(value = "/contest/tag", method = RequestMethod.GET)
    public List<String> getContestTag(int contestId) {
        List<String> result = utilService.getContestTag(contestId);
        if (result == null) {
            result.add(ContestTag.GeRen.getTag());
            result.add(ContestTag.ACM.getTag());
        }
        return result;
    }

    @RequestMapping(value = "/contest/sort", method = RequestMethod.GET)
    public List<Contest> getContestByTag(String tag) {
        return contestService.getContestByTag(tag);
    }

}
