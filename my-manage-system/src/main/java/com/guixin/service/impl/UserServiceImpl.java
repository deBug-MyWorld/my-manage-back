package com.guixin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guixin.pojo.User;
import com.guixin.mapper.UserMapper;
import com.guixin.pojo.dto.UserDto;
import com.guixin.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guixin.util.JwtTokenUtil;
import com.guixin.util.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author guixin
 * @since 2021-02-18
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisUtil redisUtil;
    @Value("${jwt.Redis_Token_Key}")
    private String Redis_Token_Key;
    /**
     * 根据用户Id查询他的角色Id
     * @param userId
     * @return
     */
    @Override
    public List<Integer> getRoleIdsByUserId(Integer userId) {
        return userMapper.getRolesByUserId(userId);
    }

    /**
     * 新增用户
     * @param userDto
     * @return
     */
    @Override
    public boolean addUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        System.out.println(user);
        user.setPassword(passwordEncoder.encode("123456"));
        // 添加用户
        boolean flag = userMapper.insert(user) > 0;
        // 绑定角色
        userDto.getRoles().stream().map(roleId -> userMapper.addUserRoles(user.getUserId(),roleId)).collect(Collectors.toList());
        return flag;
    }

    /**
     * 编辑用户
     * @param userDto
     * @return
     */
    @Override
    public boolean editUser(UserDto userDto) {

        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        boolean flag1 = userMapper.updateById(user) > 0;
        // 删除用户角色关联表
        boolean flag2 = userMapper.delUserRoles(user.getUserId());
        userDto.getRoles().stream().map(roleId ->userMapper.addUserRoles(user.getUserId(),roleId)).collect(Collectors.toList());
        redisUtil.del("user::username:"+userDto.getUsername());
        redisUtil.del("role::username:"+userDto.getUsername());
        redisUtil.del("menu::username:"+userDto.getUsername());
        redisUtil.del("menu::bulidMenu:"+userDto.getUsername());
        if (userDto.getEnabled()==0){
            redisUtil.del(Redis_Token_Key+userDto.getUsername());
        }
        return flag1&&flag2;
    }

    @Override
    public boolean delUser(Integer id) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserId,id);
        User user = userMapper.selectOne(wrapper);
        redisUtil.del("user::username:"+user.getUsername());
        redisUtil.del(Redis_Token_Key+user.getUsername());
        return userMapper.deleteById(id)>0;
    }

    @Cacheable(key = "'username:'+#p0",unless="#result == null")
    public User getUser(String username){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        return userMapper.selectOne(wrapper);
    }

}
