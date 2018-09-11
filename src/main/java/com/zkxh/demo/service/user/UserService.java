package com.zkxh.demo.service.user;

import com.zkxh.demo.dto.UserInfoDto;
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
     * @description 用户登录过程
     * @date 9:34 2018/9/10
     * @param [userLoginVOReq]
     * @auther lifeng
     * @return com.zkxh.demo.vo.UserLoginVOResp
     **/
    public UserLoginVOResp userLogin(UserLoginVOReq userLoginVOReq);


    /**
     * @description Shiro 授权过程 service 用法
     * @date 9:32 2018/9/10
     * @param [account]
     * @auther lifeng
     * @return com.zkxh.demo.dto.UserInfoDto
     **/
    public UserInfoDto findUserInfoDtoByAccount(String account);
}
