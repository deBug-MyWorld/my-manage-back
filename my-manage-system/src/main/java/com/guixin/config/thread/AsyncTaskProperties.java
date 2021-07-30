package com.guixin.config.thread;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 线程池配置属性类
 * 参考 https://juejin.im/entry/5abb8f6951882555677e9da2
 * 重写spring默认线程池的方式使用的时候，只需要加@Async注解就可以，不用去声明线程池类。
 */
@Data
@Component
@ConfigurationProperties(prefix = "task.pool")
public class AsyncTaskProperties {

    private int corePoolSize;

    private int maxPoolSize;

    private int keepAliveSeconds;

    private int queueCapacity;
}
