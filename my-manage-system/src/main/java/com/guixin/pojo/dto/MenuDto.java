package com.guixin.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MenuDto implements Serializable {
    private Integer menuId;

    private Integer menuPid;

    private String menuName;

    private String url;

    private String permission;

    private String icon;

    private Integer status;

    private String type;

    private Integer sort;

    private String component;

    private Boolean hidden;

    private List<MenuDto> children;

}
