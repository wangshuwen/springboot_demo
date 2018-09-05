package com.zkxh.demo.service.impl;

import com.zkxh.demo.common.base.service.impl.BaseService;
import com.zkxh.demo.dao.user.SysUserMapper;
import com.zkxh.demo.model.user.SysUser;
import com.zkxh.demo.model.user.SysUserExample;
import com.zkxh.demo.service.UserService;
import com.zkxh.demo.vo.UserLoginVOReq;
import com.zkxh.demo.vo.UserLoginVOResp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
//        if (!subject.isAuthenticated()){
//            UsernamePasswordToken token = new UsernamePasswordToken(username,
//                    password);
//            token.setRememberMe(rememberMe);
//        }
        UsernamePasswordToken token = new UsernamePasswordToken(userLoginVOReq.getAccount(), userLoginVOReq.getPassWord());
        subject.login(token);

        //返回菜单树
        //TODO


        //通过Shiro获取返回的User信息
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();

        //封装返回数据
        UserLoginVOResp resp = new UserLoginVOResp();
        resp.setAccount(user.getSysAccount());
        resp.setUserId(user.getSysUserid());
        resp.setUserName(user.getSysUsername());
        resp.setHeadimg(user.getSysHeadimg());
        resp.setLastLoginTime(user.getSysLastLoginTime());
        resp.setPhone(user.getSysPhonenumber());
        resp.setToken(subject.getSession().getId().toString());

        logger.info("sessionid : " + subject.getSession().getId());

        return resp;
    }
}
