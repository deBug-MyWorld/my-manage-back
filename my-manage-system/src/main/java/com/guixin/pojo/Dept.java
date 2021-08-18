package com.guixin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 部门表
 * </p>
 *
 * @author guixin
 * @since 2021-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Dept对象", description="部门表")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id值")
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Integer deptId;

    @ApiModelProperty(value = "上级部门")
    private Integer deptPid;

    @ApiModelProperty(value = "祖级列表")
    private String deptPids;

    @ApiModelProperty(value = "名称")
    private String deptName;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "状态")
    private Boolean status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
