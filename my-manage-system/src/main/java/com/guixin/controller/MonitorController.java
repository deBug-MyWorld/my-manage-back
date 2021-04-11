package com.guixin.controller;

import com.guixin.exception.AjaxResponse;
import com.guixin.service.MonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "服务监控管理")
@RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    private MonitorService monitorService;

    @GetMapping
    @ApiOperation("查询服务监控")
    public AjaxResponse MonitorQuery(){
        return AjaxResponse.success(monitorService.getService());
    }

}
