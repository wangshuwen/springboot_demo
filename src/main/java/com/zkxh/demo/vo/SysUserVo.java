package com.zkxh.demo.vo;

/**
 * @ClassName SysUser
 * @Description
 * @Auther lifeng
 * @DATE 2018/8/24 14:17
 * @Vserion v0.0.1
 */

public class SysUserVo {
    private String account;
    private String passWord;

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
