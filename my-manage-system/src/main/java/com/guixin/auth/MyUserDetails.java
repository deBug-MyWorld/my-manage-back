package com.guixin.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guixin.pojo.Role;
import com.guixin.pojo.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class MyUserDetails implements UserDetails {
    private User user;
    private List<Role> roles;
    private Collection<? extends GrantedAuthority> authorities;  //用户的权限集合


    public MyUserDetails(User user,List<Role> roles ,Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.roles =roles;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled()==1;
    }

}
