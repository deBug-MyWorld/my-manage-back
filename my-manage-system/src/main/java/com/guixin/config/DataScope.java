package com.guixin.config;

import com.guixin.pojo.Dept;
import com.guixin.pojo.Role;
import com.guixin.pojo.User;
import com.guixin.service.DeptService;
import com.guixin.service.impl.RoleServiceImpl;
import com.guixin.service.impl.UserServiceImpl;
import com.guixin.util.SecurityUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 参考https://gitee.com/guchengwuyue/yshopmall 实现数据权限
 */
@Component
public class DataScope {

    private final String[] scopeType ={"全部","本级","自定义"};

    private final UserServiceImpl userService;

    private final RoleServiceImpl roleService;

    private final DeptService deptService;

    public DataScope(UserServiceImpl userService,RoleServiceImpl roleService,DeptService deptService){
        this.userService = userService;
        this.roleService = roleService;
        this.deptService = deptService;
    }

    public Set<Integer> getDeptIds(){
        User user = userService.getUser(SecurityUtil.getCurrentUserName());

        // 用于存储部门ID
        Set<Integer> deptIds = new HashSet<>();
        // 查询用户角色
        List<Role> roleList = roleService.getRoles(user.getUsername());

        for (Role role : roleList){
            // 全部数据权限
            if (scopeType[0].equals(role.getDataScope())){
                return new HashSet<>();
            }
            // 本级数据权限
            if (scopeType[1].equals(role.getDataScope())){
                deptIds.add(user.getDeptId());
            }
            //自定义数据权限
            if (scopeType[2].equals(role.getDataScope())){
                Set<Dept> depts= deptService.findByRoleId(role.getRoleId());
                for (Dept dept:depts){
                    deptIds.add(dept.getDeptId());
                    List<Dept> deptChildren = deptService.findByPid(dept.getDeptId());
                    if (deptChildren!=null && deptChildren.size()!=0){
                        deptIds.addAll(getDeptChildren(deptChildren));
                    }
                }
            }
        }
        return deptIds;
    }
    // 使用递归的方式找到所有children 以后有时间尝试 find_in_set(deptid,deptids)
    public List<Integer> getDeptChildren(List<Dept> deptList) {
        List<Integer> list = new ArrayList<>();
        deptList.forEach(dept -> {
            if (dept!=null && dept.getStatus()){
                List<Dept> depts = deptService.findByPid(dept.getDeptId());
                if (deptList.size()!=0){
                    list.addAll(getDeptChildren(depts));
                }
                list.add(dept.getDeptId());
            }
        });
        return list;
    }

}
