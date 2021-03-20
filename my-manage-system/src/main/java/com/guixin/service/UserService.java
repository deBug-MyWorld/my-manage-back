package com.guixin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guixin.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guixin.pojo.dto.UserDto;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author guixin
 * @since 2021-02-18
 */
public interface UserService extends IService<User> {
    /**
     * 获取用户的角色
     * @param userId
     * @return
     */
    List<Integer> getRoleIdsByUserId(Integer userId);

    /**
     * 新增用户
     * @param userDto
     * @return
     */
    boolean addUser(UserDto userDto);

    /**
     * 编辑用户
     * @param userDto
     * @return
     */
    boolean editUser(UserDto userDto);

    boolean delUser(Integer id);
}
