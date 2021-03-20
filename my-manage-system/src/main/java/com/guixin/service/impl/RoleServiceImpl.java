package com.guixin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guixin.exception.CustomException;
import com.guixin.exception.CustomExceptionType;
import com.guixin.pojo.Role;
import com.guixin.mapper.RoleMapper;
import com.guixin.pojo.dto.RoleDto;
import com.guixin.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guixin.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author guixin
 * @since 2021-02-18
 */
@Service
@CacheConfig(cacheNames = "role")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public void addRole(Role role) {
        System.out.println(role);
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if (roleMapper.selectOne(wrapper.eq("role_name",role.getRoleName()))!=null){
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"角色名已存在！");
        }
        wrapper = new QueryWrapper<>();// 重置wrapper 否则默认and拼接
        if (roleMapper.selectOne(wrapper.eq("role_code",role.getRoleCode()))!=null){
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"角色代码已存在！");
        }
        roleMapper.insert(role);
    }

    @Override
    public void deleteById(Integer roleId) {
        clearCache(roleId);
        roleMapper.deleteRoleMenuById(roleId); // 删除用户角色关系表数据
        roleMapper.deleteById(roleId); // 删除角色
    }

    @Override
    public void editRole(Role role) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        Role role1 = roleMapper.selectOne(wrapper.eq("role_name",role.getRoleName()));
        if (role1 != null && !role1.getRoleId().equals(role.getRoleId())){
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"角色名已存在!");
        }
        wrapper = new QueryWrapper<>(); // 重置wrapper 否则默认and拼接
        Role role2 = roleMapper.selectOne(wrapper.eq("role_code",role.getRoleCode()));
        if (role2 !=null && !role2.getRoleId().equals(role.getRoleId())){
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"角色代码已存在！");
        }
        clearCache(role.getRoleId());
        roleMapper.updateById(role);
    }

    @Override
    public List<Integer> getMenuIdsByRoleId(Integer roleId) {
        return roleMapper.getMenuIdsByRoleId(roleId);
    }

    @Override
    public void permission(RoleDto roleDto) {
        clearCache(roleDto.getRoleId());
        roleMapper.deleteRoleMenuById(roleDto.getRoleId());
        if (roleDto.getMenuIds().size()>0){
            roleMapper.saveRoleMenu(roleDto.getRoleId(),roleDto.getMenuIds());
        }
    }

    @Cacheable(key = "'username:'+#p0")
    public List<Role> getRoles(String username){
        return roleMapper.loadRolesByUsername(username);
    }

    private void clearCache(Integer roleId){
        // 根据待删除的角色Id获取与之相关的用户名称
        List<String> nameList = roleMapper.getUserNameById(roleId);
        if (CollUtil.isNotEmpty(nameList)){
            for (String key :nameList){
                redisUtil.del("role::username:"+key);
                redisUtil.del("menu::username:"+key);
                redisUtil.del("menu::bulidMenu:"+key);
            }
        }
    }
}
