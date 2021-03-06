package com.guixin.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guixin.exception.AjaxResponse;
import com.guixin.exception.CustomException;
import com.guixin.exception.CustomExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Value("${spring.security.loginType}")
    private String loginType;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (loginType.equalsIgnoreCase("JSON")){
            response.setContentType("application/json;charset=UTF-8");
            if (exception instanceof InternalAuthenticationServiceException){
                response.getWriter().write(objectMapper.writeValueAsString(AjaxResponse.error
                        (new CustomException(CustomExceptionType.ACCESS_DENIED,"账号未激活！"))));
            } else {
                response.getWriter().write(objectMapper.writeValueAsString(AjaxResponse.error
                        (new CustomException(CustomExceptionType.USER_INPUT_ERROR,"用户名或者密码输入错误!"))));
            }
        } else {
            super.onAuthenticationFailure(request,response,exception);
        }
    }
}
