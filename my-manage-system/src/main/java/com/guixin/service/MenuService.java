package com.guixin.service;

import com.guixin.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guixin.pojo.dto.MenuDto;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author guixin
 * @since 2021-02-18
 */
public interface MenuService extends IService<Menu> {

    /**
     * 构建菜单树
     * @param menuDtos
     * @return
     */
    List<MenuDto> buildTree(List<MenuDto> menuDtos);

    /**
     * 构建vue菜单树
     * @param menuDtos
     * @return
     */
    Object bulidMenus(List<MenuDto> menuDtos);

    /**
     * 根据当前用户角色获取菜单
     * @param roleCodes
     * @return
     */

    List<MenuDto>findByRoleCodes(List<String> roleCodes,String username);

    /**
     * 获取全部的菜单
     * @return
     */
    List<MenuDto>getAllMenus();

    /**
     * 新增菜单/按钮
     * @param menuDto
     * @return
     */
    boolean addMenu(MenuDto menuDto);

    /**
     * 编辑菜单/按钮
     * @param menuDto
     * @return
     */
    boolean editMenu(MenuDto menuDto);

    /**
     * 删除菜单/按钮
     * @param menuId
     * @return
     */
    boolean delMenu(Integer menuId);
}
