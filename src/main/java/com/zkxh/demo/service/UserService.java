package com.zkxh.demo.service;

import com.zkxh.demo.model.user.SysUser;
import com.zkxh.demo.vo.UserLoginVOReq;
import com.zkxh.demo.vo.UserLoginVOResp;

/**
 * @ClassName UserService
 * @Description
 * @Auther lifeng
 * @DATE 2018/8/20 16:27
 * @Vserion v0.0.1
 */

public interface UserService {

    /**
     * @param [account]
     * @return com.zkxh.demo.model.user.SysUser
     * @description UserLogin Service 通过账号查找用户信息
     * @date 9:22 2018/9/4
     * @auther lifeng
     **/
    public SysUser findSysUserByAccount(String account);


    /**
     * @param [userLoginVOReq]
     * @return com.zkxh.demo.vo.UserLoginVOResp
     * @description 用户登录 service
     * @date 9:12 2018/9/4
     * @auther lifeng
     **/
    public UserLoginVOResp userLogin(UserLoginVOReq userLoginVOReq);
}
