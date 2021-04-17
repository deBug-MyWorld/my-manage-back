package com.guixin.controller;


import com.guixin.auth.MyUserDetails;
import com.guixin.exception.AjaxResponse;
import com.guixin.log.SysLog;
import com.guixin.pojo.Menu;
import com.guixin.pojo.Role;
import com.guixin.pojo.dto.MenuDto;
import com.guixin.pojo.vo.MenuVo;
import com.guixin.service.MenuService;
import com.guixin.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author guixin
 * @since 2021-02-18
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单管理")
public class MenuController {
    @Autowired
    MenuService menuService;

    @ApiOperation("获取当前用户的菜单树")
    @GetMapping("/build")
    public AjaxResponse buildMenus(){
        Authentication authentication = SecurityUtil.getCurrentUser();
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        List<String> roleCodes = myUserDetails.getRoles().stream().map(Role::getRoleCode).collect(Collectors.toList());
        List<MenuDto> list = menuService.findByRoleCodes(roleCodes,myUserDetails.getUser().getUsername());
        return AjaxResponse.success(menuService.bulidMenus(list));
    }

    @SysLog("查询菜单")
    @ApiOperation("获取全部菜单")
    @PreAuthorize("@pm.hasPermission('menu:list')")
    @GetMapping
    public AjaxResponse getAllMenus(){
        return AjaxResponse.success(menuService.getAllMenus());
    }

    @SysLog("新增菜单")
    @ApiOperation("新增菜单/按钮")
    @PreAuthorize("@pm.hasPermission('menu:add')")
    @PostMapping
    public AjaxResponse add(@RequestBody MenuDto menuDto){
        return AjaxResponse.success(menuService.addMenu(menuDto));
    }

    @SysLog("修改菜单")
    @ApiOperation("修改菜单/按钮")
    @PreAuthorize("@pm.hasPermission('menu:edit')")
    @PutMapping
    public AjaxResponse edit(@RequestBody MenuDto menuDto){
        return AjaxResponse.success(menuService.editMenu(menuDto));
    }

    @SysLog("删除菜单")
    @ApiOperation("删除菜单/按钮")
    @PreAuthorize("@pm.hasPermission('menu:delete')")
    @DeleteMapping
    public AjaxResponse del(@RequestBody int menuId){
        return AjaxResponse.success(menuService.delMenu(menuId));
    }
}

