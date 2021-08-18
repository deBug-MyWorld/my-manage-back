package com.guixin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guixin.auth.MyUserDetails;
import com.guixin.exception.CustomException;
import com.guixin.exception.CustomExceptionType;
import com.guixin.mapper.UserMapper;
import com.guixin.pojo.Role;
import com.guixin.mapper.RoleMapper;
import com.guixin.pojo.dto.RoleDto;
import com.guixin.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guixin.util.RedisUtil;
import com.guixin.util.SecurityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRole(RoleDto roleDto) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if (roleMapper.selectOne(wrapper.eq("role_name",roleDto.getRoleName()))!=null){
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"角色名已存在！");
        }
        wrapper = new QueryWrapper<>();// 重置wrapper 否则默认and拼接
        if (roleMapper.selectOne(wrapper.eq("role_code",roleDto.getRoleCode()))!=null){
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"角色代码已存在！");
        }
        Role role = new Role();
        BeanUtils.copyProperties(roleDto,role);
        roleMapper.insert(role); // mybatis-plus 通过反射 新增后则可以获得 id
        if (roleDto.getDeptIds()!=null && roleDto.getDeptIds().size()>0){
            roleMapper.saveRoleDept(role.getRoleId(),roleDto.getDeptIds());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer roleId) {
        clearCache(roleId);
        roleMapper.deleteRoleMenuById(roleId); // 删除角色菜单关系表数据
        roleMapper.deleteRoleDeptById(roleId); // 删除角色部门关系表数据
        roleMapper.deleteById(roleId); // 删除角色
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editRole(RoleDto roleDto) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        Role role1 = roleMapper.selectOne(wrapper.eq("role_name",roleDto.getRoleName()));
        if (role1 != null && !role1.getRoleId().equals(roleDto.getRoleId())){
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"角色名已存在!");
        }
        wrapper = new QueryWrapper<>(); // 重置wrapper 否则默认and拼接
        Role role2 = roleMapper.selectOne(wrapper.eq("role_code",roleDto.getRoleCode()));
        if (role2 !=null && !role2.getRoleId().equals(roleDto.getRoleId())){
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"角色代码已存在！");
        }
        clearCache(roleDto.getRoleId());
        Role role = new Role();
        BeanUtils.copyProperties(roleDto,role);
        roleMapper.updateById(role);
        dataPermission(roleDto);
    }

    @Override
    public List<Integer> getMenuIdsByRoleId(Integer roleId) {
        return roleMapper.getMenuIdsByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void permission(RoleDto roleDto) {
        clearCache(roleDto.getRoleId());
        roleMapper.deleteRoleMenuById(roleDto.getRoleId());
        if (roleDto.getMenuIds().size()>0){
            roleMapper.saveRoleMenu(roleDto.getRoleId(),roleDto.getMenuIds());
        }
    }

    @Override
    public void verification(Integer roleId) {
        if (userMapper.countByRole(roleId)>0){
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"所选角色存在用户关联，请解除关联再试！");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void dataPermission(RoleDto roleDto) {
        roleMapper.deleteRoleDeptById(roleDto.getRoleId());
        if (roleDto.getDeptIds().size()>0){
            roleMapper.saveRoleDept(roleDto.getRoleId(),roleDto.getDeptIds());
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
