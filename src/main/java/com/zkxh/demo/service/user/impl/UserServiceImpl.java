package com.zkxh.demo.service.user.impl;

import com.zkxh.demo.common.base.service.impl.BaseService;
import com.zkxh.demo.dao.role.SysRoleMapper;
import com.zkxh.demo.dao.sys_menu_role.SysMenuRoleMapper;
import com.zkxh.demo.dao.user.SysUserMapper;
import com.zkxh.demo.dto.UserInfoDto;
import com.zkxh.demo.model.menu.SysMenu;
import com.zkxh.demo.model.role.SysRole;
import com.zkxh.demo.model.sys_menu_role.SysMenuRole;
import com.zkxh.demo.model.user.SysUser;
import com.zkxh.demo.model.user.SysUserExample;
import com.zkxh.demo.service.user.UserService;
import com.zkxh.demo.vo.UserLoginVOReq;
import com.zkxh.demo.vo.UserLoginVOResp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @ClassName UserServiceImpl
 * @Description
 * @Auther lifeng
 * @DATE 2018/8/20 16:28
 * @Vserion v0.0.1
 */

@Service
public class UserServiceImpl extends BaseService<SysUserMapper, SysUser, SysUserExample> implements UserService {


    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysMenuRoleMapper sysMenuRoleMapper;

    @Resource
    private SysRoleMapper sysRoleMapper;

    public SysUser findSysUserByAccount(String account) {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andSysAccountEqualTo(account);
        List<SysUser> list = sysUserMapper.selectByExample(sysUserExample);
//        SysUserExample sysUserExample = new SysUserExample();
//
//        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
//        criteria.andSysAccountEqualTo(user.getAccount());
//        sysUserExample.createCriteria().andSysUsernameEqualTo(user.getAccount());
//        return null;
        return list.get(0);
    }

    @Override
    public UserLoginVOResp userLogin(UserLoginVOReq userLoginVOReq) {

        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(userLoginVOReq.getAccount(),
                    userLoginVOReq.getAccount());
            token.setRememberMe(userLoginVOReq.isRemenberMe());
        }
        UsernamePasswordToken token = new UsernamePasswordToken(userLoginVOReq.getAccount(), userLoginVOReq.getPassWord());
        subject.login(token);
        //TODO 返回菜单树另写接口
        //通过Shiro获取返回的User信息
        UserInfoDto user = (UserInfoDto) SecurityUtils.getSubject().getPrincipal();
        //封装返回数据
        UserLoginVOResp resp = new UserLoginVOResp();
        resp.setAccount(user.getSysUser().getSysAccount());
        resp.setUserId(user.getSysUser().getSysUserid());
        resp.setUserName(user.getSysUser().getSysUsername());
        resp.setHeadimg(user.getSysUser().getSysHeadimg());
        resp.setLastLoginTime(user.getSysUser().getSysLastLoginTime());
        resp.setPhone(user.getSysUser().getSysPhonenumber());
        resp.setToken(subject.getSession().getId().toString());

        logger.info("sessionid : " + subject.getSession().getId());

        return resp;
    }

    @Override
    public UserInfoDto findUserInfoDtoByAccount(String account) {
        //通过账号User所有信息
        SysUser sysUser = sysUserMapper.selectUserAndRoleByAccount(account);
        //获取 一对一关系的Role
        SysRole sysRole = sysUser.getSysRole();

        Integer roleId = sysRole.getSysRoleid();

        //根据Role ID 获取 所有的 MenuRole
        List<SysMenuRole> sysMenuRoleList = sysMenuRoleMapper.selectSysMenuRoleByRoleIdOfPermission(roleId);

        List<SysMenu> sysMenus = Collections.synchronizedList(new ArrayList<SysMenu>());

        for (SysMenuRole sysMenuRole : sysMenuRoleList)
            sysMenus.add(sysMenuRole.getSysMenu());

        UserInfoDto userInfoDto = new UserInfoDto();

        userInfoDto.setSysUser(sysUser);

        userInfoDto.setSysRole(sysRole);

        userInfoDto.setSysMenus(sysMenus);

        return userInfoDto;

    }
}
