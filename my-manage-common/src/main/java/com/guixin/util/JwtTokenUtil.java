package com.guixin.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtTokenUtil {

    private String secret;
    private Long expiration;
    private String header;
    private String Redis_Token_Key;
    private Long detect;


    @Resource
    private RedisUtil redisUtil;

    /**
     * 生成token令牌
     * 不设置过期时间，token状态由redis来维护
     * @param userDetails 用户
     * @return 令token牌
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put("sub", userDetails.getUsername());
        claims.put("create",new Date());
        String token = Jwts.builder().setClaims(claims)
//                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        redisUtil.set(Redis_Token_Key+userDetails.getUsername(),token,expiration/1000);
        return token;
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public void checkReNew(String token){
        String username = getUsernameFromToken(token);
        // 判断是否续期token,计算token的过期时间
        long time = redisUtil.getExpire(Redis_Token_Key + username)*1000;
        Date expireDate = DateUtil.offset(new Date(), DateField.MILLISECOND, (int) time);
        // 判断当前时间与过期时间的时间差
        long differ = expireDate.getTime() - System.currentTimeMillis();
        System.out.println("时间差");
        System.out.println(differ);
        if (differ <= detect) {
            long renew = time + expiration;
            redisUtil.expire(Redis_Token_Key+ username, renew);
        }
    }


    /**
     * 从令牌中获取数据声明,如果看不懂就看谁调用它
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims(); // Jwts.parser() 在解析token时会自己判断是否过期然后直接抛出异常
        } catch (Exception e){
            claims = null;
        }
        return claims;
    }

}
