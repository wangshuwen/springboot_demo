package com.zkxh.demo.service.menu.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.zkxh.demo.common.util.string.StringUtils;
import com.zkxh.demo.common.util.tree.Menu;
import com.zkxh.demo.dao.sys_menu_role.SysMenuRoleMapper;
import com.zkxh.demo.model.menu.SysMenu;
import com.zkxh.demo.model.sys_menu_role.SysMenuRole;
import com.zkxh.demo.service.menu.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName MenuServiceImpl
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/7 16:53
 * @Vserion v0.0.1
 */
@Service
public class MenuServiceImpl implements MenuService {

    private final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @Resource
    SysMenuRoleMapper sysMenuRoleMapper;

    @Override
    public JSONObject findMenusByRoleId(Integer roleId) {

        List<SysMenuRole> sysMenuRoleList = sysMenuRoleMapper.selectSysMenuRoleByRoleIdOfMenus(roleId);


        List<SysMenu> sysMenuList = Collections.synchronizedList(new ArrayList<SysMenu>());

        List<Menu> menuList = Collections.synchronizedList(new ArrayList<Menu>());

        for (SysMenuRole sysMenuRole : sysMenuRoleList)
            sysMenuList.add(sysMenuRole.getSysMenu());

        Menu menu = null;
        for (SysMenu sysMenu : sysMenuList) {
            menu = new Menu();
            menu.setId(sysMenu.getMenuId());
            menu.setName(sysMenu.getMenuName());
            menu.setIcon(sysMenu.getMenuIcon());
            menu.setUrl(sysMenu.getMenuUrl());
            menu.setParentId(sysMenu.getMenuParentId());
            menu.setOrder(sysMenu.getMenuSeq());
            menuList.add(menu);
        }

        //获取顶层菜单
        List<Menu> rootList = Collections.synchronizedList(new ArrayList<Menu>());

        for (Menu m : menuList) {
            if (m.getParentId() == 0)
                rootList.add(m);
        }

        // 为一级菜单设置子菜单，getChild是递归调用的

//        Menu menu1 = new Menu();
//        for (int i = 0 ; i < rootList.size(); i++){
//            menu1.setChildMenus(getChildSysMenu(rootList.get(i).getId(), menuList));
//
//        }

        for (Menu menu2 : rootList) {
            menu2.setChildMenus(getChildSysMenu(menu2.getId(), menuList));
        }
        //获取所有的菜单集合
//
        Map<String, Object> jsonMap = Collections.synchronizedMap(new HashMap<>());
        jsonMap.put("menu", rootList);

        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(jsonMap));
        //   JSONObject jsonObject = JSONObject.parseObject(menuList.toString());

        //  sysMenuMapper.selectMenuByParentId();
        return jsonObject;
        //return gson.toJson(jsonMap);
    }

    private List<Menu> getChildSysMenu(Integer id, List<Menu> root) {
        // 子菜单
        List<Menu> childList = Collections.synchronizedList(new ArrayList<Menu>());
        for (Menu menu : root) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (menu.getParentId() != null) {
                if (menu.getParentId().equals(id)) {
                    childList.add(menu);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (Menu menu : childList) {// 没有url子菜单还有子菜单
            // if (StringUtils.checkNull(menu.getUrl())) {
            // 递归
            menu.setChildMenus(getChildSysMenu(menu.getId(), root));
            //}
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
