package com.guixin.util;

import com.guixin.exception.CustomException;
import com.guixin.exception.CustomExceptionType;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@UtilityClass
public class SecurityUtil {
    public Authentication getCurrentUser(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getCurrentUserName(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null){
            throw new CustomException(CustomExceptionType.AUTHENTICATION_ENTRYPOINET);
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }
}
