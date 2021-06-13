package com.guixin.quartz;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MyJob {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public void test(){
        System.out.println("定时任务触发"+sdf.format(new Date()));
    }

    public void run(){
        System.out.println("run 执行成功"+sdf.format(new Date()));
    }
}
