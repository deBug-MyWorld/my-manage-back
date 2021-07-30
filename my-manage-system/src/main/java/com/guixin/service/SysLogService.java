package com.guixin.service;

import com.guixin.pojo.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 系统日志表 服务类
 * </p>
 *
 * @author guixin
 * @since 2021-04-17
 */
public interface SysLogService extends IService<SysLog> {
    @Async
    void save(String username, HttpServletRequest request, ProceedingJoinPoint joinPoint, SysLog sysLog);
}
