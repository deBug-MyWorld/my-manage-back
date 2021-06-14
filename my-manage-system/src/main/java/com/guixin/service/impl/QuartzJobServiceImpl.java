package com.guixin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guixin.auth.MyUserDetails;
import com.guixin.exception.CustomException;
import com.guixin.exception.CustomExceptionType;
import com.guixin.pojo.QuartzJob;
import com.guixin.mapper.QuartzJobMapper;
import com.guixin.quartz.SpringBeanJob;
import com.guixin.service.QuartzJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guixin.util.SecurityUtil;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * <p>
 * quartz定时任务表 服务实现类
 * </p>
 *
 * @author guixin
 * @since 2021-06-11
 */
@Service
public class QuartzJobServiceImpl extends ServiceImpl<QuartzJobMapper, QuartzJob> implements QuartzJobService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private QuartzJobMapper quartzJobMapper;

    private final String JOB_DATA_KEY = "job_data_key";

    @Override
    public void runJob(JobDataMap jobDataMap) {
        // 将任务对象从dataMap中取出
        QuartzJob quartzJob = (QuartzJob) jobDataMap.get(JOB_DATA_KEY);
        String beanName = quartzJob.getBeanName();
        String methodName = quartzJob.getMethodName();
        try {
            Object object = applicationContext.getBean(beanName);
            Method method = object.getClass().getDeclaredMethod(methodName);
            method.invoke(object);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("任务执行失败，任务名称："+quartzJob.getJobName());
            System.out.println("------------------------------------------------------");
            if (quartzJob.getPauseAfterFailure()!=null && quartzJob.getPauseAfterFailure()==1){
                // 暂停
                quartzJob.setStatus("2");
                pause(quartzJob);
            }
        }
    }

    @Override
    public void addJob(QuartzJob quartzJob) {
        // 构建job信息
        JobDetail jobDetail = JobBuilder.newJob(SpringBeanJob.class)
                .storeDurably()
                .withIdentity(quartzJob.getJobName())
                .withDescription(quartzJob.getDescription())
                .build();


        // 构建触发器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJob.getCron())
                .withMisfireHandlingInstructionDoNothing();
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(quartzJob.getJobName())
                .withSchedule(cronScheduleBuilder)
                .build();

        // 更新表中数据
        QuartzJob job = quartzJobMapper.selectOne(new QueryWrapper<QuartzJob>().eq("job_name",quartzJob.getJobName()));
        Authentication authentication = SecurityUtil.getCurrentUser();
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        if (job !=null){
            quartzJob.setUpdateBy(myUserDetails.getUser().getUsername());
            quartzJobMapper.updateById(quartzJob);
        } else {
            quartzJob.setCreateBy(myUserDetails.getUser().getUsername());
            quartzJobMapper.insert(quartzJob);
        }
        // 更新完数据后重新查询一次，防止insert后id为null
        quartzJob = quartzJobMapper.selectOne(new QueryWrapper<QuartzJob>().eq("job_name",quartzJob.getJobName()));

        // 将任务对象放入dataMap
        jobDetail.getJobDataMap().put(JOB_DATA_KEY,quartzJob);
        System.out.println(quartzJob);
        try {
            String name = quartzJob.getJobName();
            JobKey jobKey = JobKey.jobKey(name);
            // 如果任务已经存在，就重置定时任务
            if (scheduler.checkExists(jobKey)){
                scheduler.rescheduleJob(new TriggerKey(name),cronTrigger);
                scheduler.addJob(jobDetail,true);
            } else {
                scheduler.scheduleJob(jobDetail,cronTrigger);
            }
            // 如果一开始就是暂停的，则不启动
            if (quartzJob.getStatus().equals("2")){
                scheduler.pauseJob(jobKey);
                System.out.println("一开始就是暂停，不启动");
            }

        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void deleteJob(QuartzJob quartzJob) {
        TriggerKey triggerKey = TriggerKey.triggerKey(quartzJob.getJobName());
        try {
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移出触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(JobKey.jobKey(quartzJob.getJobName()));

            quartzJobMapper.deleteById(quartzJob.getJobId());
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR,"删除任务失败！");
        }
    }

    @Override
    public void pause(QuartzJob quartzJob) {
        System.out.println("暂停任务："+quartzJob.getJobName());
        JobKey jobKey = JobKey.jobKey(quartzJob.getJobName());
        try {
            quartzJobMapper.updateById(quartzJob);
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR,"暂停任务失败！");
        }
    }

    @Override
    public void resume(QuartzJob quartzJob) {
        System.out.println("恢复任务:"+quartzJob.getJobName());
        JobKey jobKey = JobKey.jobKey(quartzJob.getJobName());
        try {
            quartzJobMapper.updateById(quartzJob);
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR,"恢复任务失败！");
        }
    }

    @Override
    public void triggerJob(QuartzJob quartzJob) {
        JobKey jobKey = JobKey.jobKey(quartzJob.getJobName());
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR,"手动执行一次任务失败！");
        }
    }
}
