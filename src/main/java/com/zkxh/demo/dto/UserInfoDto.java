package com.zkxh.demo.dto;

import com.zkxh.demo.model.menu.SysMenu;
import com.zkxh.demo.model.role.SysRole;
import com.zkxh.demo.model.user.SysUser;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName UserInfoDto
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/7 13:47
 * @Vserion v0.0.1
 */

public class UserInfoDto implements Serializable {

    private SysUser sysUser;

    private SysRole sysRole;

    private List<SysMenu> sysMenus;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public List<SysMenu> getSysMenus() {
        return sysMenus;
    }

    public void setSysMenus(List<SysMenu> sysMenus) {
        this.sysMenus = sysMenus;
    }
}
