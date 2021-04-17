package com.guixin.service.impl;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guixin.pojo.SysLog;
import com.guixin.mapper.SysLogMapper;
import com.guixin.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guixin.util.AddressUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 系统日志表 服务实现类
 * </p>
 *
 * @author guixin
 * @since 2021-04-17
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Resource
    private SysLogMapper sysLogMapper;
    @Override
    public void save(String username, ProceedingJoinPoint joinPoint,SysLog sysLog) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        com.guixin.log.SysLog aopLog = method.getAnnotation(com.guixin.log.SysLog.class);
        sysLog.setDescription(aopLog.value());
        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";
        sysLog.setMethodName(methodName);
        sysLog.setMethod(request.getMethod());
        sysLog.setCreateBy(username);
        sysLog.setRequestUri(request.getRequestURI());
        // 通过hutool获取ip
        String ip = ServletUtil.getClientIP(request);
        sysLog.setIp(ip);
        sysLog.setAddr(AddressUtil.getAddressByIp(ip));
        sysLog.setParams(HttpUtil.toParams(request.getParameterMap()));
        sysLogMapper.insert(sysLog);
    }
}
