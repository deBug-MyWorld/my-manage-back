package com.guixin.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class UserDto implements Serializable {
    private Integer userId;

    private String username;

    private String gender;

    private String phone;

    private String email;

    private String avatar;

    private Integer enabled;

    private List<Integer> roles;
}
