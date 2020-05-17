package cn.watermelon.watermelonbackend.controller;

import cn.watermelon.watermelonbackend.entity.Contest;
import cn.watermelon.watermelonbackend.enumeration.ContestTag;
import cn.watermelon.watermelonbackend.enumeration.ProblemTag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class ProController {

    @RequestMapping(value = "tag/problem", method = RequestMethod.GET)
    List<String> getProblemTag() {
        List<String> result = new ArrayList<>();
        ProblemTag[] problemTags = ProblemTag.values();
        for (ProblemTag problemTag: problemTags) {
            result.add(problemTag.getDesc());
        }
        return  result;
    }

    @RequestMapping(value = "tag/contest", method = RequestMethod.GET)
    List<String> getContestTag() {
        List<String> result = new ArrayList<>();
        ContestTag[] contestTags = ContestTag.values();
        for (ContestTag contestTag: contestTags) {
            result.add(contestTag.getTag());
        }
        return  result;
    }



}
