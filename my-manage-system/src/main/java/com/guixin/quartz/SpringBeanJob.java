package com.guixin.quartz;

import com.guixin.service.QuartzJobService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;

public class SpringBeanJob implements Job {

    @Resource
    private QuartzJobService quartzJobService;

    @Override
    public void execute(JobExecutionContext context) {
        quartzJobService.runJob(context.getMergedJobDataMap());
    }
}
