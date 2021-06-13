package com.guixin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guixin.exception.AjaxResponse;
import com.guixin.log.SysLog;
import com.guixin.pojo.QuartzJob;
import com.guixin.service.QuartzJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * quartz定时任务表 前端控制器
 * </p>
 *
 * @author guixin
 * @since 2021-06-11
 */
@RestController
@RequestMapping("/quartz")
@Api(tags = "定时任务")
public class QuartzJobController {
    @Autowired
    private QuartzJobService quartzJobService;

    @SysLog("查询定时任务列表")
    @ApiOperation("分页查询定时任务")
    @PreAuthorize("@pm.hasPermission('quartz:list')")
    @GetMapping
    public AjaxResponse jobPage(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,@RequestParam(value = "jobName",required = false) String jobName){
        QueryWrapper<QuartzJob> wrapper = new QueryWrapper<>();
        Page<QuartzJob> page = new Page<>(pageNum,pageSize);
        if (!ObjectUtils.isEmpty(jobName)){
            wrapper.lambda().like(QuartzJob::getJobName,jobName);
        }
        wrapper.orderByDesc("create_time");
        return AjaxResponse.success(quartzJobService.page(page,wrapper));
    }

    @ApiOperation("新增/修改定时任务")
    @SysLog("新增/修改定时任务")
    @PostMapping
    @PreAuthorize("@pm.hasPermission('quartz:exe')")
    public AjaxResponse AddOrSave(@RequestBody QuartzJob quartzJob){
        String msg = "";
        if (ObjectUtils.isEmpty(quartzJob.getJobId())){
            System.out.println("新增任务------------>"+quartzJob.getJobName());
            msg ="新增任务成功";
        } else {
            System.out.println("修改任务------------>"+quartzJob.getJobName());
            msg ="修改任务成功";
        }
        quartzJobService.addJob(quartzJob);
        return AjaxResponse.success(msg);
    }

    @ApiOperation("删除定时任务")
    @SysLog("删除定时任务")
    @PreAuthorize("@pm.hasPermission('quartz:delete')")
    @DeleteMapping
    public AjaxResponse delete(@RequestBody QuartzJob quartzJob){
        quartzJobService.deleteJob(quartzJob);
        return AjaxResponse.success("删除定时任务成功");
    }

    @PreAuthorize("@pm.hasPermission('quartz:trigger')")
    @ApiOperation("手动执行一次任务")
    @SysLog("手动执行一次任务")
    @PutMapping("trigger")
    public AjaxResponse triggerJob(@RequestBody QuartzJob quartzJob){
        quartzJobService.triggerJob(quartzJob);
        return AjaxResponse.success("任务执行成功");
    }

    @SysLog("暂停定时任务")
    @ApiOperation("暂停定时任务")
    @PreAuthorize("@pm.hasPermission('quartz:pause')")
    @PutMapping("pause")
    public AjaxResponse pause(@RequestBody QuartzJob quartzJob){
        quartzJobService.pause(quartzJob);
        return AjaxResponse.success("暂停任务成功");
    }

    @SysLog("恢复定时任务")
    @ApiOperation("恢复定时任务")
    @PreAuthorize("@pm.hasPermission('quartz:resume')")
    @PutMapping("resume")
    public AjaxResponse resume(@RequestBody QuartzJob quartzJob){
        quartzJobService.resume(quartzJob);
        return AjaxResponse.success("恢复任务成功");
    }

    @GetMapping("check")
    @ApiOperation("cron表达式是否正确")
    public AjaxResponse check(String cron){
        return AjaxResponse.success(CronExpression.isValidExpression(cron));
    }
}

