package com.guixin.mapper;

import com.guixin.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author guixin
 * @since 2021-02-18
 */

public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu>loadPermissionByRoleCode(@Param("permissions") List<String> permissions);

    List<Menu>loadMenuTreeByRoleCodes(@Param("roleCodes")List<String> roleCodes);

    void setChildPids(@Param("menuId") Integer menuId,@Param("pids")String pids);

    void deleteMenuRoleById(Integer menuId);

    void deleteMenuById(String menuId);

    List<Integer> getRoleByMenuId(Integer menuId);
}
