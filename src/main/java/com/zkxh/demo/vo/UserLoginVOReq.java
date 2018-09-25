package com.zkxh.demo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName UserLoginVOReq
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/4 9:05
 * @Vserion v0.0.1
 */
@ApiModel(value = "UserLoginVOReq", description = "登录信息字段")
public class UserLoginVOReq {
    @ApiModelProperty(name = "account", value = "账号", required = true)
    private String account;

    @ApiModelProperty(name = "passWord", value = "密码", required = true)
    private String passWord;

    @ApiModelProperty(name = "remenberMe", value = "记住密码")
    private boolean remenberMe;


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isRemenberMe() {
        return remenberMe;
    }

    public void setRemenberMe(boolean remenberMe) {
        this.remenberMe = remenberMe;
    }

    @Override
    public String toString() {
        return "SysUserVo{" +
                "account='" + account + '\'' +
                ", passWord='" + passWord + '\'' +
                ", remenberMe=" + remenberMe +
                '}';
    }
}
