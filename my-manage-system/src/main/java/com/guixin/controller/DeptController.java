package com.guixin.controller;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guixin.config.DataScope;
import com.guixin.exception.AjaxResponse;
import com.guixin.log.SysLog;
import com.guixin.pojo.Dept;
import com.guixin.pojo.dto.DeptDto;
import com.guixin.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author guixin
 * @since 2021-08-16
 */
@RestController
@RequestMapping("/dept")
@Api(tags = "部门管理")
public class DeptController {

    @Autowired
    private DeptService deptService;
    @Autowired
    private DataScope dataScope;

    @GetMapping
    @SysLog("查询部门")
    @ApiOperation("查询部门")
    @PreAuthorize("@pm.hasPermission('dept:list')")
    public AjaxResponse getDeptList(@RequestParam(value = "deptName",required = false) String deptName){
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        if (CollUtil.isNotEmpty(dataScope.getDeptIds())){
            wrapper.in("dept_id",dataScope.getDeptIds());
        }
        if (!ObjectUtils.isEmpty(deptName)){
            wrapper.like("dept_name",deptName);
        }
        return AjaxResponse.success(deptService.buildDeptTree(wrapper));
    }

    @PostMapping
    @SysLog("新增部门")
    @PreAuthorize("@pm.hasPermission('dept:add')")
    @ApiOperation("新增部门")
    public AjaxResponse add(@RequestBody DeptDto deptDto){
        return AjaxResponse.success(deptService.addDept(deptDto));
    }

    @PutMapping
    @ApiOperation("编辑部门")
    @SysLog("编辑部门")
    @PreAuthorize("@pm.hasPermission('dept:edit')")
    public AjaxResponse edit(@RequestBody DeptDto deptDto){
        return AjaxResponse.success(deptService.editDept(deptDto));
    }

    @DeleteMapping
    @SysLog("删除部门")
    @ApiOperation("删除部门")
    @PreAuthorize("@pm.hasPermission('dept:delete')")
    public AjaxResponse del(@RequestBody int deptId){
        Set<Integer> deptIds = new HashSet<>();
        List<Dept> deptList =deptService.findByPid(deptId);
        deptIds.add(deptId);
        if (CollUtil.isNotEmpty(deptList)){
            deptIds = deptService.getDeleteDepts(deptList, deptIds);
        }
        // 验证是否被角色或用户关联
        deptService.verification(deptIds);
        return AjaxResponse.success(deptService.delDept(deptIds));
    }

    @ApiOperation("通过角色Id获取部门集合")
    @GetMapping("/deptIds/{roleId}")
    public AjaxResponse getDeptIdsByRoleId(@PathVariable("roleId") Integer roleId){
        Set<Integer> deptIds = new HashSet<>();
        Set<Dept> deptSet = deptService.findByRoleId(roleId);
        for (Dept dept:deptSet){
            deptIds.add(dept.getDeptId());
        }
        return AjaxResponse.success(deptIds);
    }
}

