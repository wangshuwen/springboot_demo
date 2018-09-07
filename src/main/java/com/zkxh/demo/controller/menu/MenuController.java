package com.zkxh.demo.controller.menu;

import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.dto.UserInfoDto;
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
        UserInfoDto userInfoDto = (UserInfoDto) SecurityUtils.getSubject().getPrincipal();
        Integer roleId = userInfoDto.getSysRole().getSysRoleid();
        //menuService.findMenusByRoleId(roleId);

        return ResultUtil.jsonToStringSuccess();
    }
}
