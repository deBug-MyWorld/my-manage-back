package com.guixin.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class DeptDto implements Serializable {
    private Integer deptId;

    private Integer deptPid;

    private String deptName;

    private Integer sort;

    private Boolean status;

    private Date createTime;

    private List<DeptDto> children;
}
