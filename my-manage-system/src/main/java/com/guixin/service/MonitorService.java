package com.guixin.service;

import java.util.Map;

public interface MonitorService {
    /**
     * 获取系统服务
     * @return Map<String,Object>
     */
    Map<String,Object> getService();
}
