package com.guixin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
// 在mybatis-plus中配置了 mapperscan
public class MyManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyManageApplication.class,args);
    }
}
