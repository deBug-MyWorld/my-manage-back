package com.guixin.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoleDto implements Serializable {
    private Integer roleId;

    private List<Integer> menuIds;
}
