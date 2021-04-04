package com.guixin.auth;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guixin.exception.AjaxResponse;
import com.guixin.util.DateUtil;
import com.guixin.util.JwtTokenUtil;
import com.guixin.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
@Slf4j
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = jwtTokenUtil.getUsernameFromToken(request.getHeader(jwtTokenUtil.getHeader())) ;

        if (StrUtil.isNotEmpty(username)){
            redisUtil.del("user::username:"+username);
            redisUtil.del(jwtTokenUtil.getRedis_Token_Key()+username);
        }
        log.info("=== 用户【{}】在 {} 退出了系统 ===", username, DateUtil.nowString());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(AjaxResponse.success()));
    }
}
