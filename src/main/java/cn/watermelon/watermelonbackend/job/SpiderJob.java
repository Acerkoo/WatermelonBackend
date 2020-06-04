package cn.watermelon.watermelonbackend.job;

import cn.watermelon.watermelonbackend.entity.Spider;
import cn.watermelon.watermelonbackend.utils.SpiderUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

@Component
public class SpiderJob {

    @Value("python3 /home/admin/source/main.py")
//    @Value("python3 /Users/szki/Desktop/main.py")
    private String spiderProcess;

    private List<Spider> contests;

    public SpiderJob() {
        contests = new ArrayList<>();
    }

    public List<Spider> getContestInfo() {
        if (contests.size() == 0) {
            scheduledWork();
        }
        return contests;
    }

    @Scheduled(fixedRate = 1000 * 60 * 60)
    private void scheduledWork() {
        List<Spider> tmpContest = spiderWork();
        contests = new ArrayList<>();
        Collections.sort(contests, new Comparator<Spider>() {
            @Override
            public int compare(Spider o1, Spider o2) {
                return o1.getEndTime().compareTo(o2.getEndTime());
            }
        });
        List<Spider> pastContest = new ArrayList<>();
        List<Spider> preContest = new ArrayList<>();
        Date date = new Date();
        for (Spider spider : tmpContest) {
            if (spider.getEndTime().compareTo(date) < 0) {
                pastContest.add(spider);
            } else {
                if (spider.getStartTime().compareTo(date) < 0) {
                    contests.add(spider);
                } else {
                    preContest.add(spider);
                }
            }
        }
        preContest.addAll(pastContest);
        contests.addAll(preContest);

        System.out.println("spider");

    }

    private List<Spider> spiderWork() {
        Process process;
        List<Spider> list = new ArrayList<>();
        try {
            process = Runtime.getRuntime().exec(spiderProcess);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                list.add(SpiderUtil.getInstance().getSpider(line));
//                System.out.println(line);
            }
            in.close();
            process.waitFor();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
