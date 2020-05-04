package cn.watermelon.watermelonbackend.controller;

import cn.watermelon.watermelonbackend.dto.ProblemDTO;
import cn.watermelon.watermelonbackend.service.ProblemService;
import cn.watermelon.watermelonbackend.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProblemController {
    @Autowired
    private ProblemService problemService;

    @RequestMapping(value = "/problem/all", method = RequestMethod.GET)
    public List<ProblemDTO> getAll() {
        return ConvertUtil.problemDTOList(problemService.findAll());
    }

    @RequestMapping(value = "/problem/name", method = RequestMethod.GET)
    public List<ProblemDTO> getProblemByName(String name) {
        return ConvertUtil.problemDTOList(problemService.findProblemByName(name));
    }

    @RequestMapping(value = "/problem/id", method = RequestMethod.GET)
    public List<ProblemDTO> getProblemById(int id) {
        return ConvertUtil.problemDTOList(problemService.findProblemById(id));
    }

}
