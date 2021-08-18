package com.guixin.mapper;

import com.guixin.pojo.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author guixin
 * @since 2021-08-16
 */
public interface DeptMapper extends BaseMapper<Dept> {

    Set<Dept> findByRoleId(Integer roleId);

    int selectNormalChildrenDeptById(Integer deptId);

    List<Dept> selectChildrenDeptById(Integer deptId);

    /**
     * 批量更新 修改子元素关系
     * @param depts /
     */
    void updateDeptChildren(@Param("depts") List<Dept> depts);
    /**
     * 修改所在部门的父级部门状态
     * @param dept 部门
     */
    void updateDeptStatus(Dept dept);

}
