package com.guixin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guixin.pojo.Dept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guixin.pojo.dto.DeptDto;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author guixin
 * @since 2021-08-16
 */
public interface DeptService extends IService<Dept> {

    /**
     * 构建部门树
     * @return 树结构部门数据
     */
    List<DeptDto> buildDeptTree(QueryWrapper<Dept> wrapper);

    /**
     * 根据角色Id查询部门
     * @param roleId 角色id
     * @return 部门
     */
    Set<Dept> findByRoleId(Integer roleId);

    List<Dept> findByPid(Integer pid);

    /**
     *  新增部门
     * @param deptDto /
     * @return /
     */
    boolean addDept(DeptDto deptDto);

    /**
     * 修改部门
     * @param deptDto /
     * @return /
     */
    boolean editDept(DeptDto deptDto);

    /**
     * 根据Id删除部门包括子部门
     * @param deptIds /
     * @return /
     */
    boolean delDept(Set<Integer> deptIds);

    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param deptId 部门ID
     * @return 子部门数
     */
    int selectNormalChildrenDeptById(Integer deptId);
    /**
     * 获取待删除的部门
     * @param deptList /
     * @param deptIds /
     * @return /
     */
    Set<Integer> getDeleteDepts(List<Dept> deptList, Set<Integer> deptIds);

    void verification(Set<Integer> deptIds);
}
