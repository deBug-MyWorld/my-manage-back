package com.guixin.log;


import cn.hutool.extra.servlet.ServletUtil;
import com.guixin.auth.MyUserDetails;
import com.guixin.pojo.SysLog;
import com.guixin.service.SysLogService;
import com.guixin.util.AddressUtil;
import com.guixin.util.SecurityUtil;
import com.guixin.util.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


@Aspect
@Component
@Slf4j
public class LogAspect {
    @Autowired
    private SysLogService sysLogService;
    ThreadLocal<Long> currentTime = new ThreadLocal<>();
    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.guixin.log.SysLog)")
    public void logPointCut(){
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result;
        currentTime.set(System.currentTimeMillis());
        // 接口响应数据
        result = point.proceed();
        SysLog sysLog = new SysLog();
        sysLog.setType("INFO");
        sysLog.setTime(System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        sysLog.setMethod(request.getMethod());
        // 通过hutool获取ip
        String ip = ServletUtil.getClientIP(request);
        sysLog.setIp(ip);
        sysLog.setAddr(AddressUtil.getAddressByIp(ip));
        sysLogService.save(getUsername(),request,point,sysLog);
        return result;
    }

    @AfterThrowing(pointcut = "logPointCut()",throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint,Throwable e){
        SysLog sysLog = new SysLog();
        sysLog.setType("ERROR");
        sysLog.setTime(System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        sysLog.setExceptionDetail(ThrowableUtil.getStackTrace(e));
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        sysLog.setMethod(request.getMethod());
        // 通过hutool获取ip
        String ip = ServletUtil.getClientIP(request);
        sysLog.setIp(ip);
        sysLog.setAddr(AddressUtil.getAddressByIp(ip));
        sysLogService.save(getUsername(),request, (ProceedingJoinPoint) joinPoint,sysLog);
    }

    public String getUsername(){
        Authentication authentication = SecurityUtil.getCurrentUser();
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        return myUserDetails.getUsername();
    }
}
