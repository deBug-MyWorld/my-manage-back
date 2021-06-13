package com.guixin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * quartz定时任务表
 * </p>
 *
 * @author guixin
 * @since 2021-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="QuartzJob对象", description="quartz定时任务表")
public class QuartzJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "job_id", type = IdType.AUTO)
    private Integer jobId;

    @ApiModelProperty(value = "任务名")
    private String jobName;

    @ApiModelProperty(value = "任务描述")
    private String description;

    @ApiModelProperty(value = "cron表达式")
    private String cron;

    @ApiModelProperty(value = "任务执行时调用的spring的bean名称")
    private String beanName;

    @ApiModelProperty(value = "任务方法名")
    private String methodName;

    @ApiModelProperty(value = "任务状态（1运行中 2暂停中 3未启动)")
    private String status;

    @ApiModelProperty(value = "任务失败后是否暂停")
    private Integer pauseAfterFailure;

    @ApiModelProperty(value = "创建者")
    @JsonIgnore
    private String createBy;

    @ApiModelProperty(value = "更新者")
    @JsonIgnore
    private String updateBy;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @JsonIgnore
    private Date updateTime;


}
