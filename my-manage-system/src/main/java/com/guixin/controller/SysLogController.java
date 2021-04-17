package com.guixin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guixin.exception.AjaxResponse;
import com.guixin.pojo.SysLog;
import com.guixin.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 系统日志表 前端控制器
 * </p>
 *
 * @author guixin
 * @since 2021-04-17
 */
@RestController
@RequestMapping("/logs")
@Api(tags = "日志管理")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @ApiOperation("分页获取日志信息")
    @PreAuthorize("@pm.hasPermission('log:list')")
    @GetMapping
    public AjaxResponse logPage(@RequestParam("pageNum") int pageNum,
                                @RequestParam("pageSize") int pageSize,
                                @RequestParam(value = "query",required = false) String query,
                                @RequestParam(value = "time",required = false)List<Timestamp> time){
        QueryWrapper<SysLog> queryWrapper = new QueryWrapper<>();
        Page<SysLog> page = new Page<>(pageNum,pageSize);
        if (!ObjectUtils.isEmpty(query)){
            queryWrapper.lambda().like(SysLog::getCreateBy,query)
            .or().like(SysLog::getIp,query)
            .or().like(SysLog::getType,query)
            .or().like(SysLog::getRequestUri,query)
            .or().like(SysLog::getDescription,query);
        }
        if (!ObjectUtils.isEmpty(time)){
            System.out.println(time);
            queryWrapper.between("create_time", time.get(0), time.get(1));
        }
        queryWrapper.orderByDesc("create_time");
        return AjaxResponse.success(sysLogService.page(page,queryWrapper));
    }

    @ApiOperation("分页获取当前用户的日志信息")
    @GetMapping("/user")
    public AjaxResponse userLog(@RequestParam("pageNum") int pageNum,
                                @RequestParam("pageSize") int pageSize,
                                @RequestParam("username") String username){
        QueryWrapper<SysLog> queryWrapper = new QueryWrapper<>();
        Page<SysLog> page = new Page<>(pageNum,pageSize);
        queryWrapper.eq("create_by",username);
        queryWrapper.orderByDesc("create_time");
        return AjaxResponse.success(sysLogService.page(page,queryWrapper));
    }
}

