package com.zkxh.demo.controller.menu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.common.util.ClazzUtil;
import com.zkxh.demo.service.menu.MenuService;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName MenuController
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/7 16:48
 * @Vserion v0.0.1
 */
@RestController
@Api(value = "MenuController", tags = "菜单操作接口")
public class MenuController {

    @Resource
    private MenuService menuService;

    @GetMapping("user/treeMenu")
    public String getTreeMenu() {

        Object sysUser = SecurityUtils.getSubject().getPrincipal();

        ClazzUtil clazzUtil = new ClazzUtil();

        Integer roleId = (Integer) clazzUtil.getFieldValueByName("roleId", sysUser);

        JSONObject menuTreeOfJsonObj = menuService.findMenusByRoleId(roleId);
        return ResultUtil.jsonToStringSuccess(menuTreeOfJsonObj);
    }
}
