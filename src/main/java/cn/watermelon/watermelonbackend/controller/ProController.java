package cn.watermelon.watermelonbackend.controller;

import cn.watermelon.watermelonbackend.enumeration.ProblemTag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tag")
public class ProController {

    @RequestMapping(value = "/problem", method = RequestMethod.GET)
    List<String> getProblemTag() {
        List<String> result = new ArrayList<>();
        ProblemTag[] problemTags = ProblemTag.values();
        for (ProblemTag problemTag: problemTags) {
            result.add(problemTag.getDesc());
        }
        return  result;
    }



}
