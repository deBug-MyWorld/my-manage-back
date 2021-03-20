package com.guixin.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guixin.exception.AjaxResponse;
import com.guixin.util.DateUtil;
import com.guixin.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Value("${spring.security.loginType}")
    private String loginType;
    @Resource
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        String username =authentication.getName();
        log.info("=== 用户【{}】在 {} 登录了系统 ===", username, DateUtil.nowString());
        if (loginType.equalsIgnoreCase("JSON")){
            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
            String jwtToken = jwtTokenUtil.generateToken(myUserDetails);
            Map<String,Object> authInfo = new HashMap<String,Object>(){{
                put("token",jwtToken);
                put("user",myUserDetails.getUser());
                put("roles",myUserDetails.getRoles());
                put("permissions",myUserDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
            }};
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(AjaxResponse.success(authInfo)));
        }else {
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }
}
