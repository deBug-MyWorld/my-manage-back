package com.guixin.controller;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guixin.auth.MyUserDetails;
import com.guixin.config.DataScope;
import com.guixin.exception.AjaxResponse;
import com.guixin.exception.CustomException;
import com.guixin.exception.CustomExceptionType;
import com.guixin.log.SysLog;
import com.guixin.pojo.User;
import com.guixin.pojo.dto.UserDto;
import com.guixin.pojo.vo.UserPassVo;
import com.guixin.service.AliOssService;
import com.guixin.service.DeptService;
import com.guixin.service.UserService;
import com.guixin.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author guixin
 * @since 2021-02-18
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private DataScope dataScope;
    @Autowired
    private DeptService deptService;

    @ApiOperation("测试后台是否成功启动")
    @GetMapping("/index")
    public String index() {
        return "Backend service started successfully";
    }


    @ApiOperation("获取用户信息")
    @GetMapping("/info")
    public AjaxResponse getUserInfo(){
        Authentication authentication = SecurityUtil.getCurrentUser();
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        HashMap<String,Object> map = new HashMap<String,Object>(){{
            put("user",myUserDetails.getUser());
            put("roles",myUserDetails.getRoles());
            put("permissions",myUserDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        }};
        return AjaxResponse.success(map);
    }

    @SysLog("查询用户")
    @ApiOperation("分页获取全部用户信息")
    @PreAuthorize("@pm.hasPermission('user:list')")
    @GetMapping
    public AjaxResponse getUserList(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,
                                    @RequestParam(value = "username",required = false) String username,
                                    @RequestParam(value = "deptId",required = false) Integer deptId){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Page<User> page = new Page<>(pageNum,pageSize);
        Set<Integer> deptSet = new HashSet<>();
        Set<Integer> result = new HashSet<>();
        if (!ObjectUtils.isEmpty(username)){
            wrapper.lambda().like(User::getUsername,username);
        }
        if (!ObjectUtils.isEmpty(deptId)){
            deptSet.add(deptId);
            deptSet.addAll(dataScope.getDeptChildren(deptService.findByPid(deptId)));
        }
        // 数据权限
        Set<Integer> deptIds = dataScope.getDeptIds();
        // 查询条件不为空，并且数据权限不为空取交集
        if (!CollectionUtils.isEmpty(deptIds) && !CollectionUtils.isEmpty(deptSet)){
            // 取交集
            result.addAll(deptSet);
            result.retainAll(deptIds);
            wrapper.in("dept_id",result);
            // 若无交集，则代表无数据权限
            if (result.size()== 0){
                return AjaxResponse.success(null);
            } else {
                return AjaxResponse.success(userService.page(page,wrapper));
            }
        } else {
            // 否则取并集
            result.addAll(deptSet);
            result.addAll(deptIds);
            wrapper.in("dept_id",result);
            return AjaxResponse.success(userService.page(page,wrapper));
        }
    }

    @SysLog("删除用户")
    @ApiOperation("逻辑删除用户")
    @PreAuthorize("@pm.hasPermission('user:delete')")
    @DeleteMapping
    public AjaxResponse del(@RequestBody int id){
        return AjaxResponse.success(userService.delUser(id));
    }

    @SysLog("新增用户")
    @ApiOperation("新增用户")
    @PreAuthorize("@pm.hasPermission('user:add')")
    @PostMapping
    public AjaxResponse add(@RequestBody UserDto userDto){
        return AjaxResponse.success(userService.addUser(userDto));
    }

    @SysLog("编辑用户")
    @ApiOperation("编辑用户")
    @PreAuthorize("@pm.hasPermission('user:edit')")
    @PutMapping
    public AjaxResponse edit(@RequestBody UserDto userDto){
        return AjaxResponse.success(userService.editUser(userDto));
    }

    @SysLog("个人中心编辑信息")
    @ApiOperation("个人中心编辑信息")
    @PostMapping("/center")
    public AjaxResponse center(@RequestBody User user){
        userService.updateCenter(user);
        return AjaxResponse.success();
    }

    @SysLog("修改密码")
    @ApiOperation("修改密码")
    @PostMapping("/updatePass")
    public AjaxResponse updatePass(@RequestBody UserPassVo passVo){
        userService.updatePass(passVo);
        return AjaxResponse.success();
    }



    @ApiOperation("检查用户名是否已存在")
    @GetMapping("checkName/{username}")
    public AjaxResponse checkName(@PathVariable("username") String username){
        boolean flag = userService.list(null).stream().anyMatch(user -> user.getUsername().equals(username));
        if (flag){
            return AjaxResponse.error(new CustomException(CustomExceptionType.USER_INPUT_ERROR,"用户名已存在"));
        } else {
            return AjaxResponse.success();
        }
    }

    @ApiOperation("查询用户的角色")
    @GetMapping("userRoles/{userId}")
    public AjaxResponse userRoles(@PathVariable("userId") int userId){
        return AjaxResponse.success(userService.getRoleIdsByUserId(userId));
    }

}

