package com.guixin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2  //开启Swagger2
public class SwaggerConfig {
    //配置Swagger的bean实例
    @Bean
    public Docket docket(Environment environment){

        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev");

        //获取项目环境
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("归心")
                .enable(flag)           //配置是否启用Swagger，如果是false，在浏览器将无法访问
                .select()           // 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors.basePackage("com.guixin.controller"))
                //paths() 过滤什么路径 即这里只扫描请求以/guixin开头的接口
//                .paths(PathSelectors.ant("/guixin/**"))
                .build()
                .securitySchemes(security());

    }

    //配置Swagger信息
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("归心zero", "https://space.bilibili.com/179120470", "1037554070@qq.com");
        return new ApiInfo(
                "归心zero的SwaggerAPI文档", // 标题
                "选择大于努力", // 描述
                "v1.0", // 版本
                "https://space.bilibili.com/179120470", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }

    private List<ApiKey> security(){
       List<ApiKey> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeyList;
    }
}
