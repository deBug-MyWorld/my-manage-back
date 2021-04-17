package com.guixin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;

import java.sql.Timestamp;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统日志表
 * </p>
 *
 * @author guixin
 * @since 2021-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysLog对象", description="系统日志表")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    @ApiModelProperty(value = "日志类型")
    private String type;

    @ApiModelProperty(value = "日志描述")
    private String description;

    @ApiModelProperty(value = "操作ip地址")
    private String ip;

    @ApiModelProperty(value = "操作地址")
    private String addr;

    @ApiModelProperty(value = "请求uri")
    private String requestUri;


    @ApiModelProperty(value = "请求的方法名")
    private String methodName;

    @ApiModelProperty(value = "请求方式(POST,GET,PUT,DELETE)")
    private String method;

    @ApiModelProperty(value = "请求提交参数")
    private String params;

    @ApiModelProperty(value = "请求时间")
    private Long time;


    @ApiModelProperty(value = "错误详情")
    private byte[] exceptionDetail;

    @ApiModelProperty(value = "操作人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;



}
