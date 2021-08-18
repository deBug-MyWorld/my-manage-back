package com.guixin.mapper;

import com.guixin.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author guixin
 * @since 2021-02-18
 */

public interface RoleMapper extends BaseMapper<Role> {
    List<Role> loadRolesByUsername(@Param("username") String username);

    void deleteRoleMenuById(Integer roleId);

    List<Integer> getMenuIdsByRoleId(Integer roleId);

    void saveRoleMenu(@Param("roleId")Integer roleId,@Param("menuIds")List<Integer> menuIds);

    List<String> getUserNameById(Integer roleId);

    void deleteRoleDeptById(Integer roleId);

    void saveRoleDept(@Param("roleId") Integer roleId,@Param("deptIds")List<Integer> deptIds);

    int countByDepts(@Param("deptIds")Set<Integer> deptIds);
}
