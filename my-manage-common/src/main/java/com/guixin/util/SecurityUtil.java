package com.guixin.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityUtil {
    public Authentication getCurrentUser(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
