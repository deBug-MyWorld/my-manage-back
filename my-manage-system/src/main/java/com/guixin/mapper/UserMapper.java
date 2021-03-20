package com.guixin.mapper;

import com.guixin.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author guixin
 * @since 2021-02-18
 */

public interface UserMapper extends BaseMapper<User> {
    List<Integer> getRolesByUserId(Integer userId);

    Boolean addUserRoles(@Param("userId") Integer userId,@Param("roleId") Integer roleId);

    Boolean delUserRoles(@Param("userId") Integer userId);
}
