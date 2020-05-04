package cn.watermelon.watermelonbackend.controller;

import cn.watermelon.watermelonbackend.dto.ProblemDTO;
import cn.watermelon.watermelonbackend.entity.Contest;
import cn.watermelon.watermelonbackend.service.ContestService;
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
    ContestService contestService;

    @RequestMapping(value = "/contest/all", method = RequestMethod.GET)
    public List<Contest> getAllContest() {
        return contestService.getAllContest();
    }

    @RequestMapping(value = "/contest/problem", method = RequestMethod.GET)
    public List<ProblemDTO> getProblem(int contestId) {
        return ConvertUtil.problemDTOList(contestService.findContestProblem(contestId));
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

}
