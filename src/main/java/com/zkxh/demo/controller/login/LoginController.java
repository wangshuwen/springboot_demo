package com.zkxh.demo.controller.login;

import com.zkxh.demo.common.base.controller.impl.BaseController;
import com.zkxh.demo.common.enums.ResultEnum;
import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.service.UserService;
import com.zkxh.demo.vo.UserLoginVOReq;
import com.zkxh.demo.vo.UserLoginVOResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName LoginController
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/7 10:01
 * @Vserion v0.0.1
 */

@RestController
@Api(value = "LoginController", tags = {"用户登录操作接口"})
public class LoginController extends BaseController {


    //注入UserService
    @Resource
    UserService userService;


    /**
     * @description 用户登录接口
     * @date 10:02 2018/9/7
     * @param [user]
     * @auther lifeng
     * @return java.lang.String
     **/
    @ApiOperation(value = "用户登录方法接口", notes = "根据User对象的account ，passWord 参数进行登录验证")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "java.lang.String", name = "account", value = "账号信息", required = true),
            @ApiImplicitParam(dataType = "java.lang.String", name = "passWord", value = "密码信息", required = true)
    })
    @PostMapping("/login")
    public String userLogin(@RequestBody UserLoginVOReq user) {
        UserLoginVOResp resp = userService.userLogin(user);
        if (resp != null) {
            return ResultUtil.jsonToStringSuccess(resp);
        } else {
            return ResultUtil.jsonToStringError(ResultEnum.FAILED);
        }
    }
}
