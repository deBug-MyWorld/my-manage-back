package com.guixin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guixin.exception.CustomException;
import com.guixin.exception.CustomExceptionType;
import com.guixin.mapper.RoleMapper;
import com.guixin.pojo.Menu;
import com.guixin.mapper.MenuMapper;
import com.guixin.pojo.Role;
import com.guixin.pojo.dto.MenuDto;
import com.guixin.pojo.vo.MenuMetaVo;
import com.guixin.pojo.vo.MenuVo;
import com.guixin.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guixin.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author guixin
 * @since 2021-02-18
 */
@Service
@CacheConfig(cacheNames = "menu")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private RoleMapper roleMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public List<MenuDto> buildTree(List<MenuDto> menuDtos) {
        List<MenuDto> trees = new ArrayList<>();
        for (MenuDto menuDto : menuDtos){
            if (menuDto.getMenuPid() == null){
                trees.add(menuDto);
            }
            for (MenuDto it : menuDtos){
                if (menuDto.getMenuId().equals(it.getMenuPid())){
                    if (menuDto.getChildren() == null){
                        menuDto.setChildren(new ArrayList<>());
                    }
                    menuDto.getChildren().add(it);
                }
            }
        }
        return trees;
    }

    @Override
    public List<MenuVo> bulidMenus(List<MenuDto> menuDtos) {
        List<MenuVo> list = new LinkedList<>();
        menuDtos.forEach(menuDto -> {
            if (menuDto!=null){
                List<MenuDto> menuDtoList = menuDto.getChildren();
                MenuVo menuVo = new MenuVo();
                menuVo.setName(menuDto.getMenuName());
                // 一级目录要加 ”/“
                 menuVo.setPath(menuDto.getMenuPid()==null ? "/"+menuDto.getUrl() : menuDto.getUrl());
                menuVo.setHidden(menuDto.getHidden());
                if (menuDto.getMenuPid() == null){
                    menuVo.setComponent(ObjectUtils.isEmpty(menuDto.getComponent())?"Layout":menuDto.getComponent());
                }else if (!ObjectUtils.isEmpty(menuDto.getComponent())){
                    menuVo.setComponent(menuDto.getComponent());
                }
                menuVo.setMeta(new MenuMetaVo(menuDto.getMenuName(),menuDto.getIcon()));
                if (menuDtoList != null && menuDtoList.size()!=0){ // 有子菜单
                    menuVo.setChildren(bulidMenus(menuDtoList));
                    menuVo.setRedirect("noredirect");
                }else if (menuDto.getMenuPid() == null){  // 处理是一级菜单并且没有子菜单的情况
                    MenuVo menuVo1 = new MenuVo();
                    menuVo1.setMeta(menuVo.getMeta());
                    menuVo1.setPath("index");
                    menuVo1.setName(menuVo.getName());
                    menuVo1.setComponent(menuVo.getComponent());
                    menuVo.setName(null);
                    menuVo.setComponent("Layout");
                    menuVo.setMeta(null);
                    List<MenuVo> list1 =new ArrayList<>();
                    list1.add(menuVo1);
                    menuVo.setChildren(list1);
                }
                list.add(menuVo);
            }
        });
        return list;
    }

    @Override
    @Cacheable(key = "'bulidMenu:'+#p1")
    public List<MenuDto> findByRoleCodes(List<String> roleCodes,String username) { // username 用于缓存标志
        List<Menu> menus = menuMapper.loadMenuTreeByRoleCodes(roleCodes); // 返回可以访问的菜单，不包括按钮
        return getMenuDtos(menus);
    }

    @Override
    public List<MenuDto> getAllMenus() {
        List<Menu> menus = menuMapper.selectList(null);
        return getMenuDtos(menus);
    }

    @Override
    public boolean addMenu(MenuDto menuDto) {
        QueryWrapper<Menu>wrapper = new QueryWrapper<>();
        if (menuMapper.selectOne(wrapper.eq("menu_name",menuDto.getMenuName()))!= null){
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"名称已经存在！");
        }
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDto,menu);
        if (menu.getMenuPid()==0){
            menu.setMenuPid(null);
        }
        setMenuPids(menu);
        return menuMapper.insert(menu)>0;
    }

    @Override
    public boolean editMenu(MenuDto menuDto) {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        if (menuDto.getMenuId().equals(menuDto.getMenuPid())){
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"上级不能为自己!");
        }
        Menu menu1 = menuMapper.selectOne(wrapper.eq("menu_name",menuDto.getMenuName()));
        if (menu1 != null && !menu1.getMenuId().equals(menuDto.getMenuId())){
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,"名称已经存在！");
        }
        if (menuDto.getMenuPid() == 0){
            menuDto.setMenuPid(null);
        }
        // 更改pids
        // 将当前的pids置空，调用setPids，将子目录pids更新
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDto,menu);
        setMenuPids(menu);
        if (ObjectUtils.isEmpty(menu.getMenuPids())){
            setChildPids(menuDto,menu.getMenuId()+"");
        } else {
            setChildPids(menuDto,menu.getMenuPids()+","+menu.getMenuId());
        }
        delCache(menuDto.getMenuId());
        return menuMapper.updateById(menu)>0;
    }

    @Override
    public boolean delMenu(Integer menuId) {
        delCache(menuId);
        menuMapper.deleteMenuRoleById(menuId); // 删除菜单角色表信息
        menuMapper.deleteMenuById(menuId.toString()); // 删除子节点
        return menuMapper.deleteById(menuId)>0;
    }

    @Cacheable(key = "'username:'+#p0")
    public List<Menu> getMenus(String username){
        List<Role> roles = roleMapper.loadRolesByUsername(username);
        List<String>roleCodes = roles.stream().map(Role::getRoleCode).collect(Collectors.toList());
        return menuMapper.loadPermissionByRoleCode(roleCodes);
    }

    private void setChildPids(MenuDto superior,String pids){
        if (superior.getChildren()!=null){
            menuMapper.setChildPids(superior.getMenuId(),pids);
            for (MenuDto menuDto:superior.getChildren()){
                setChildPids(menuDto,pids+","+menuDto.getMenuId());
            }
        }
    }

    private void setMenuPids(Menu child){
        List<Menu> menus = menuMapper.selectList(null);
        for (Menu menu:menus){
            if (menu.getMenuId().equals(child.getMenuPid())){
                if (ObjectUtils.isEmpty(menu.getMenuPids())){
                    child.setMenuPids(menu.getMenuId()+"");
                } else {
                    child.setMenuPids(menu.getMenuPids()+","+child.getMenuPid());
                }
            }
        }
    }

    private List<MenuDto> getMenuDtos(List<Menu> menus) {
        List<MenuDto> menuDtos = menus.stream().map(menu -> {
            MenuDto menuDto = new MenuDto();
            BeanUtils.copyProperties(menu,menuDto);
            return menuDto;
        }).collect(Collectors.toList());
        return buildTree(menuDtos);
    }

    private void delCache(Integer menuId){
        List<Integer> roleIds = menuMapper.getRoleByMenuId(menuId);
        if (roleIds.size()>0){
            for (Integer role:roleIds){
                List<String>users = roleMapper.getUserNameById(role);
                for (String user:users){
                    redisUtil.del("menu::username:"+user);
                    redisUtil.del("menu::bulidMenu:"+user);
                }
            }
        }
    }
}
