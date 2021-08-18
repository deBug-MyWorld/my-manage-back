package com.guixin.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class RoleDto implements Serializable {

    private Integer roleId;

    private String roleName;

    private Integer status;

    private Date createTime;

    private String roleCode;

    private String dataScope;

    private List<Integer> deptIds;

    private List<Integer> menuIds;
}
