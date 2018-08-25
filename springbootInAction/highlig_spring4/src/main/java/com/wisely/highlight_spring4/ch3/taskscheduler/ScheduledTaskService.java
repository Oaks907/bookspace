package com.wisely.highlight_spring4.ch3.taskscheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by haifei on 25/8/2018.
 */
@Service
public class ScheduledTaskService {

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

  @Scheduled(fixedDelay = 5000)
  public void reportCurrentTime() {
    System.out.println("每间隔5秒执行一次 " + dateFormat.format(new Date()));
  }

  @Scheduled(cron = "0 44 9 ? * *")
  public void fixTimeExecution() {
    System.out.println("在指定时间 " + dateFormat.format(new Date()) + "执行");
  }
}
