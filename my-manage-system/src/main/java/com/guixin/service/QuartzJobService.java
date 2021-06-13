package com.guixin.service;

import com.guixin.pojo.QuartzJob;
import com.baomidou.mybatisplus.extension.service.IService;
import org.quartz.JobDataMap;

/**
 * <p>
 * quartz定时任务表 服务类
 * </p>
 *
 * @author guixin
 * @since 2021-06-11
 */
public interface QuartzJobService extends IService<QuartzJob> {
    void runJob(JobDataMap jobDataMap);

    void addJob(QuartzJob quartzJob);

    void deleteJob(QuartzJob quartzJob);

    void pause(QuartzJob quartzJob);

    void resume(QuartzJob quartzJob);

    void triggerJob(QuartzJob quartzJob);
}
