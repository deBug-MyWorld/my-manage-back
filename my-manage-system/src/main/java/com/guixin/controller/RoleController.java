package com.guixin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guixin.exception.AjaxResponse;
import com.guixin.log.SysLog;
import com.guixin.pojo.Role;
import com.guixin.pojo.dto.RoleDto;
import com.guixin.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author guixin
 * @since 2021-02-18
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色管理")
public class RoleController {
    @Autowired
    RoleService roleService;

    @ApiOperation("获取全部角色列表")
    @GetMapping("/all")
    public AjaxResponse getRoleList(){
        return AjaxResponse.success(roleService.list(null));
    }

    @SysLog("查询角色")
    @ApiOperation("分页获取角色列表")
    @PreAuthorize("@pm.hasPermission('role:list')")
    @GetMapping
    public AjaxResponse PageRoleList(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam(value = "query",required = false) String query){
        QueryWrapper<Role>wrapper = new QueryWrapper<>();
        Page<Role> page = new Page<>(pageNum,pageSize);
        if (!ObjectUtils.isEmpty(query)){
            System.out.println(query);
            wrapper.lambda().like(Role::getRoleName,query).or().like(Role::getRoleCode,query);
        }
        return AjaxResponse.success(roleService.page(page,wrapper));
    }

    @SysLog("删除角色")
    @ApiOperation("逻辑删除角色")
    @PreAuthorize("@pm.hasPermission('role:delete')")
    @DeleteMapping
    public AjaxResponse del(@RequestBody int roleId){
        roleService.verification(roleId);
        roleService.deleteById(roleId);
        return AjaxResponse.success();
    }

    @SysLog("新增角色")
    @ApiOperation("新增角色")
    @PreAuthorize("@pm.hasPermission('role:add')")
    @PostMapping
    public AjaxResponse add(@RequestBody RoleDto roleDto){
        roleService.addRole(roleDto);
        return AjaxResponse.success();
    }

    @SysLog("修改角色")
    @ApiOperation("编辑角色")
    @PreAuthorize("@pm.hasPermission('role:edit')")
    @PutMapping
    public AjaxResponse edit(@RequestBody RoleDto roleDto){
        roleService.editRole(roleDto);
        return AjaxResponse.success();
    }

    @ApiOperation("通过角色Id获取菜单集合")
    @GetMapping("/menuIds/{roleId}")
    public AjaxResponse getMenuIdsByRoleId(@PathVariable("roleId") Integer roleId){
        return AjaxResponse.success(roleService.getMenuIdsByRoleId(roleId));
    }


    @SysLog("分配权限")
    @ApiOperation("分配权限")
    @PreAuthorize("@pm.hasPermission('role:permission')")
    @PostMapping("/permission")
    public AjaxResponse Permission(@RequestBody RoleDto roleDto){
        roleService.permission(roleDto);
        return AjaxResponse.success();
    }
}

