package com.guixin.auth;

import com.guixin.util.JwtTokenUtil;
import com.guixin.util.RedisUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    MyUserDetailsService myUserDetailsService;
    @Resource
    JwtTokenUtil jwtTokenUtil;
    @Resource
    RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(request.getRequestURI());
        if (getTokenIgnoreUrl().contains(request.getRequestURI())){
            filterChain.doFilter(request,response);
            return;
        }
        String jwtToken = request.getHeader(jwtTokenUtil.getHeader());
        if (!ObjectUtils.isEmpty(jwtToken)){
            String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            String token = (String) redisUtil.get(jwtTokenUtil.getRedis_Token_Key()+username);
            // 用户名不为空，token没过期，并且在springsecurity中没有认证
            if (username !=null && jwtToken.equals(token) && SecurityContextHolder.getContext().getAuthentication() == null){
                System.out.println("没认证");
                UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
                jwtTokenUtil.checkReNew(jwtToken); // token 续签
                // 给使用该JWT令牌的用户进行授权
                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
    // 直接放过不需要检查token的请求
    private List<String>getTokenIgnoreUrl(){
        List<String>tokenIgnoreUrls = new ArrayList<>();
        tokenIgnoreUrls.add("/refreshtoken");
        tokenIgnoreUrls.add("/certification");
        return tokenIgnoreUrls;
    }
}
