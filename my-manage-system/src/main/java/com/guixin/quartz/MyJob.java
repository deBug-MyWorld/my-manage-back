package com.guixin.quartz;

import org.springframework.stereotype.Component;

import java.io.File;
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
    
    public void dumpDataBase(){
        System.out.println("数据库导出----------------------->"+sdf.format(new Date()));
        String filePath = "G:\\database\\";
        String dbName = "my_manage";
        String username = "root";
        String password = "123456";
        File dumpDir = new File(filePath);
        if (!dumpDir.exists()){
            dumpDir.mkdirs();
        }
        String cmd =  "mysqldump -u"+ username +" -p"+password +" --databases "+ dbName + " -r "
                + filePath + dbName+new java.util.Date().getTime()+ ".sql";
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            System.out.println("数据库备份成功！！");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
