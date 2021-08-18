package com.guixin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guixin.exception.CustomException;
import com.guixin.exception.CustomExceptionType;
import com.guixin.mapper.RoleMapper;
import com.guixin.mapper.UserMapper;
import com.guixin.pojo.Dept;
import com.guixin.mapper.DeptMapper;
import com.guixin.pojo.dto.DeptDto;
import com.guixin.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author guixin
 * @since 2021-08-16
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<DeptDto> buildDeptTree(QueryWrapper<Dept> wrapper) {
        List<Dept> depts =deptMapper.selectList(wrapper);
        List<DeptDto> deptDtos = depts.stream().map(dept -> {
            DeptDto deptDto = new DeptDto();
            BeanUtils.copyProperties(dept,deptDto);
            return deptDto;
        }).collect(Collectors.toList());
        List<DeptDto> dtos = new ArrayList<>();
        for (DeptDto deptDto:deptDtos){
            // pid为0 顶级结点
            if (deptDto.getDeptPid() == 0){
                dtos.add(deptDto);
            }
            for (DeptDto dto:deptDtos){
                if (deptDto.getDeptId().equals(dto.getDeptPid())){
                    if (deptDto.getChildren()==null){
                        deptDto.setChildren(new ArrayList<>());
                    }
                    deptDto.getChildren().add(dto);
                }
            }
        }
        if (CollUtil.isEmpty(dtos)){
            dtos = deptDtos;
        }
        return dtos;
    }

    @Override
    public Set<Dept> findByRoleId(Integer roleId) {
        return deptMapper.findByRoleId(roleId);
    }

    @Override
    public List<Dept> findByPid(Integer pid) {
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper.eq("dept_pid",pid);
        return deptMapper.selectList(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    // 参考 若依 实现
    public boolean addDept(DeptDto deptDto) {
        Dept dept = new Dept();
        BeanUtils.copyProperties(deptDto,dept);
        // 如果父节点不为正常状态,则不允许新增子节点
        Dept info = deptMapper.selectById(dept.getDeptPid());
        if (!info.getStatus()){
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR,"部门停用，不允许新增");
        }
        dept.setDeptPids(info.getDeptPids()+","+dept.getDeptPid());
        return deptMapper.insert(dept)>0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    // 参考 若依 实现
    public boolean editDept(DeptDto deptDto) {
        if (deptDto.getDeptPid()!=null && deptDto.getDeptId().equals(deptDto.getDeptPid())){
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"上级不能为自己");
        }
        if (!deptDto.getStatus() && selectNormalChildrenDeptById(deptDto.getDeptId())>0){
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"该部门包含未停用的子部门");
        }
        Dept dept = new Dept();
        BeanUtils.copyProperties(deptDto,dept);
        // 新的父节点部门信息
        Dept newParentDept = deptMapper.selectById(dept.getDeptPid());
        // 还未更改的部门信息
        Dept oldDept = deptMapper.selectById(dept.getDeptId());
        // 新的父节点部门信息为空，说明是顶级结点,不为空则更新祖级列表
        if (!ObjectUtils.isEmpty(newParentDept) && !ObjectUtils.isEmpty(oldDept)){
            String newDeptPids = newParentDept.getDeptPids()+","+newParentDept.getDeptId();
            String oldDeptPids = oldDept.getDeptPids();
            dept.setDeptPids(newDeptPids);
            // 更新子结点pids信息
            updateDeptChildren(dept.getDeptId(),newDeptPids,oldDeptPids);
        }
        if (dept.getStatus()){
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            deptMapper.updateDeptStatus(dept);
        }
        return deptMapper.updateById(dept)>0;
    }


    /**
     * 修改子元素关系
     *
     * @param deptId 被修改的部门ID
     * @param newDeptPids 新的父ID集合
     * @param oldDeptPids 旧的父ID集合
     */
    private void updateDeptChildren(Integer deptId, String newDeptPids, String oldDeptPids) {
        List<Dept> children = deptMapper.selectChildrenDeptById(deptId);
        for (Dept child:children){
            // 使用replace替换原先的
            child.setDeptPids(child.getDeptPids().replace(oldDeptPids,newDeptPids));
        }
        if (children.size()>0){
            // 批量更新
            deptMapper.updateDeptChildren(children);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delDept(Set<Integer> deptIds) {
        return deptMapper.deleteBatchIds(deptIds)>0;
    }

    @Override
    public int selectNormalChildrenDeptById(Integer deptId) {
        return deptMapper.selectNormalChildrenDeptById(deptId);
    }

    @Override
    public Set<Integer> getDeleteDepts(List<Dept> deptList, Set<Integer> deptIds) {
        for (Dept dept:deptList){
            deptIds.add(dept.getDeptId());
            List<Dept> depts = findByPid(dept.getDeptId());
            if (depts!=null && depts.size()!=0){
                getDeleteDepts(depts,deptIds);
            }
        }
        return deptIds;
    }

    @Override
    public void verification(Set<Integer> deptIds) {
        if (userMapper.countByDepts(deptIds)>0){
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"所选部门存在用户关联，请解除后再试！");
        }
        if (roleMapper.countByDepts(deptIds)>0){
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"所选部门存在角色关联，请解除后再试！");
        }
    }

}
