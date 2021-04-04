package com.guixin.exception;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

// 在数据返回给前端前做最后一步拦截处理 统一http状态码
@Component
@ControllerAdvice(basePackages = "com.guixin.controller")
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override  // 支持哪些方法参数，响应类 都支持返回true
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 如果响应结果是json数据类型
        if (mediaType.equalsTypeAndSubtype(MediaType.APPLICATION_JSON)){
            if (((AjaxResponse)body).getCode() != 999){ // http状态码有的自定义的异常代码   999不是http状态码
                // 自定义响应码和http状态码一致
                serverHttpResponse.setStatusCode(HttpStatus.valueOf(((AjaxResponse)body).getCode()));
                return body;
            } else {
                return AjaxResponse.error(new CustomException(CustomExceptionType.OTHER_ERROR));
            }

        }
        return null;
    }
}
