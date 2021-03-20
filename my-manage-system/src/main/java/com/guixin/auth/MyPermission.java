package com.guixin.auth;

import com.guixin.util.SecurityUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component(value = "pm")
public class MyPermission {
    public boolean hasPermission(String permission){
        Authentication currentUser = SecurityUtil.getCurrentUser();
        MyUserDetails myUserDetails = (MyUserDetails) currentUser.getPrincipal();
        if (ObjectUtils.isEmpty(myUserDetails)){
            return false;
        }
        List<String> myPermission =myUserDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        System.out.println(myPermission);
        return myPermission.contains("ROLE_admin") ||myPermission.stream().filter(StringUtils::hasText).anyMatch(x -> PatternMatchUtils.simpleMatch(permission,x));
    }
}
