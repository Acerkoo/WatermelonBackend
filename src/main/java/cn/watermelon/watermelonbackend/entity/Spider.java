package cn.watermelon.watermelonbackend.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Spider implements Serializable {
    private int contestId;
    private String ojName;
    private int ojId;
    private String link;
    private Date startTime;
    private Date endTime;
    private String contestName;
}