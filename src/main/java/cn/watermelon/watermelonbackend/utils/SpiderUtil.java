package cn.watermelon.watermelonbackend.utils;

import cn.watermelon.watermelonbackend.entity.Spider;

import java.util.Date;

public class SpiderUtil {
    private static volatile SpiderUtil spiderUtil;

    private SpiderUtil() {
    }

    public static SpiderUtil getInstance() {
        if (spiderUtil == null) {
            synchronized (SpiderUtil.class) {
                if (spiderUtil == null) {
                    spiderUtil = new SpiderUtil();
                }
            }
        }
        return spiderUtil;
    }

    public static Spider getSpider(String spiderString) {
        int cnt = 0;
        int i = 0;
        Spider spider = new Spider();
        while (i < spiderString.length()) {
            while (i < spiderString.length() && spiderString.charAt(i) != ':') {
                i++;
            }
            if (i == spiderString.length()) {
                break;
            }
            cnt = cnt + 1;
            if (cnt == 1) {
                int contestId = 0;
                while (i < spiderString.length() && spiderString.charAt(i) != ',') {
                    i = i + 1;
                    if (spiderString.charAt(i) >= '0' && spiderString.charAt(i) <= '9') {
                        contestId = contestId * 10 + (spiderString.charAt(i) - '0');
                    }
                }
                spider.setContestId(contestId);
            } else if (cnt == 2) {
                String ojName = new String();
                i = i + 1;
                while (i < spiderString.length() && spiderString.charAt(i) != ',') {
                    if (spiderString.charAt(i) != '\'') {
                        ojName += spiderString.charAt(i);
                    }

                    i = i + 1;
                }
                spider.setOjName(ojName);
            } else if (cnt == 3) {
                int ojId = 0;
                while (i < spiderString.length() && spiderString.charAt(i) != ',') {
                    i = i + 1;

                    if (spiderString.charAt(i) >= '0' && spiderString.charAt(i) <= '9') {
                        ojId = ojId * 10 + (spiderString.charAt(i) - '0');
                    }
                }
                spider.setOjId(ojId);
            } else if (cnt == 4) {
                String link = new String();
                i = i + 1;
                while (i < spiderString.length() && spiderString.charAt(i) != ',') {
                    if (spiderString.charAt(i) != '\'') {
                        link += spiderString.charAt(i);
                    }
                    i = i + 1;
                }
                spider.setLink(link);
            } else if (cnt == 5) {

                Long startTime = 0l;
                while (i < spiderString.length() && spiderString.charAt(i) != ',') {
                    i = i + 1;
                    if (spiderString.charAt(i) >= '0' && spiderString.charAt(i) <= '9') {
                        startTime = startTime * 10 + (spiderString.charAt(i) - '0');
                    }
                }
                Date startDate = new Date(startTime);
                spider.setStartTime(startDate);
            } else if (cnt == 6) {
                Long endTime = 0l;
                while (i < spiderString.length() && spiderString.charAt(i) != ',') {
                    i = i + 1;
                    if (spiderString.charAt(i) >= '0' && spiderString.charAt(i) <= '9') {

                        endTime = endTime * 10 + (spiderString.charAt(i) - '0');
                    }
                }
                Date endDate = new Date(endTime);
                spider.setEndTime(endDate);
            } else if (cnt == 7) {
                String contestName = new String();
                i = i + 1;
                while (i < spiderString.length() && spiderString.charAt(i) != '}') {
                    if (spiderString.charAt(i) != '\'') {
                        contestName += spiderString.charAt(i);
                    }

                    i = i + 1;
                }
                spider.setContestName(contestName);
            }
        }
        return spider;
    }
}
