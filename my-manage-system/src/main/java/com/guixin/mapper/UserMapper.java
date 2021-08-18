package com.guixin.mapper;

import com.guixin.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

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

    void updatePass(@Param("username") String username,@Param("password") String password);

    void updateAvatar(@Param("username") String username,@Param("avatar") String avatar);

    int countByDepts(@Param("deptIds")Set<Integer> deptIds);

    int countByRole(@Param("roleId")Integer roleId);
}
