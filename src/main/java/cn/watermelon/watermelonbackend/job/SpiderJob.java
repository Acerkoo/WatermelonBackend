package cn.watermelon.watermelonbackend.job;

import cn.watermelon.watermelonbackend.entity.Spider;
import cn.watermelon.watermelonbackend.utils.SpiderUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
            contests = spiderWork();
        }
        return contests;
    }

    @Scheduled(fixedRate = 1000 * 60 * 60)
    private void scheduledWork() {
        contests = spiderWork();
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
