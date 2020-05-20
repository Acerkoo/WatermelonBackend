package cn.watermelon.watermelonbackend.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Spider {
    private int contestId;
    private String ojName;
    private int ojId;
    private String link;
    private Date startTime;
    private Date endTime;
    private String contestName;
}