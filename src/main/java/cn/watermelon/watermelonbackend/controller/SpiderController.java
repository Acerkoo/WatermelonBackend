package cn.watermelon.watermelonbackend.controller;

import cn.watermelon.watermelonbackend.entity.Spider;
import cn.watermelon.watermelonbackend.job.SpiderJob;
import cn.watermelon.watermelonbackend.utils.SpiderUtil;
//import org.python.util.PythonInterpreter;
import io.lettuce.core.dynamic.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/spider")
public class SpiderController {

    @Autowired
    private SpiderJob spiderJob;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Spider> SpiderCalender() {
        List<Spider> result = spiderJob.getContestInfo();
        return result;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Spider Test() {
        String s = "{'contestId': 3820, 'ojName': 'CodeChef', 'ojId': 5, 'link': 'https://www.codechef.com/MAY20?itm_campaign=contest_listing', 'startTime': 1588325400000, 'endTime': 1589189400000, 'contestName': 'May Challenge 2020'}";
        return SpiderUtil.getInstance().getSpider(s);
    }
}
