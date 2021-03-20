package com.guixin.service;

import com.guixin.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guixin.pojo.dto.RoleDto;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author guixin
 * @since 2021-02-18
 */
public interface RoleService extends IService<Role> {
    /**
     * 新增角色
     * @param role
     * @return
     */
    void addRole(Role role);

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    void deleteById(Integer roleId);

    /**
     * 修改角色
     * @param role
     * @return
     */
    void editRole(Role role);

    /**
     * 根据角色Id获取菜单
     * @param roleId
     * @return
     */
    List<Integer> getMenuIdsByRoleId(Integer roleId);

    void permission(RoleDto roleDto);
}
