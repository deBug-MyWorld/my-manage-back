package com.guixin.auth;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guixin.exception.CustomException;
import com.guixin.exception.CustomExceptionType;
import com.guixin.mapper.MenuMapper;
import com.guixin.mapper.RoleMapper;
import com.guixin.mapper.UserMapper;
import com.guixin.pojo.Menu;
import com.guixin.pojo.Role;
import com.guixin.pojo.User;
import com.guixin.service.UserService;
import com.guixin.service.impl.MenuServiceImpl;
import com.guixin.service.impl.RoleServiceImpl;
import com.guixin.service.impl.UserServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Resource
    private UserServiceImpl userService;
    @Resource
    private RoleServiceImpl roleService;
    @Resource
    private MenuServiceImpl menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("myUserDetailsService");
        if (username == null || username.length() == 0){
            throw new RuntimeException("用户名不能为空");
        }
        User user = userService.getUser(username);
        if (user == null){
            throw new UsernameNotFoundException(String.format("%s用户不存在",username));
        }
        if (user.getEnabled()==0){
            System.out.println("禁用");
            throw new InternalAuthenticationServiceException("账号未激活！");
            // 抛出了异常就会被springsecurity的异常处理捕捉到，变成InternalAuthenticationServiceException
//            throw new CustomException(CustomExceptionType.ACCESS_DENIED,"账号未激活！");
        }
        // 获取角色
        List<Role> roles = roleService.getRoles(username);
        List<String> roleCodes = roles.stream().map(Role::getRoleCode).collect(Collectors.toList());
        // 获取权限
        List<Menu> menus = menuService.getMenus(username);
        List<String> permissions = menus.stream().map(Menu::getPermission).filter(permission ->!ObjectUtils.isEmpty(permission)).collect(Collectors.toList());
        // 合并角色和权限
        permissions.addAll(roleCodes.stream().map(roleCode ->"ROLE_"+roleCode).collect(Collectors.toList()));
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", permissions));
        return new MyUserDetails(user,roles,authorities);
    }

}
