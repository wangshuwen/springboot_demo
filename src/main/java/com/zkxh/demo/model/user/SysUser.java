package com.zkxh.demo.model.user;

import com.zkxh.demo.common.base.entity.BaseEntity;
import com.zkxh.demo.model.role.SysRole;

import java.util.Date;

public class SysUser extends BaseEntity {

    private SysRole sysRole;

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.sys_userid
     *
     * @mbg.generated
     */
    private Integer sysUserid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.sys_username
     *
     * @mbg.generated
     */
    private String sysUsername;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.sys_account
     *
     * @mbg.generated
     */
    private String sysAccount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.sys_password
     *
     * @mbg.generated
     */
    private String sysPassword;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.sys_nickname
     *
     * @mbg.generated
     */
    private String sysNickname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.sys_headimg
     *
     * @mbg.generated
     */
    private String sysHeadimg;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.sys_register_time
     *
     * @mbg.generated
     */
    private Date sysRegisterTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.sys_last_modify_time
     *
     * @mbg.generated
     */
    private Date sysLastModifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.sys_phonenumber
     *
     * @mbg.generated
     */
    private String sysPhonenumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.sys_last_login_time
     *
     * @mbg.generated
     */
    private Date sysLastLoginTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.user_group_id
     *
     * @mbg.generated
     */
    private Integer userGroupId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.sys_user_level
     *
     * @mbg.generated
     */
    private String sysUserLevel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.enabled
     *
     * @mbg.generated
     */
    private Boolean enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.create_by
     *
     * @mbg.generated
     */
    private Integer createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.user_salt
     *
     * @mbg.generated
     */
    private String userSalt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.role_id
     *
     * @mbg.generated
     */
    private Integer roleId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.sys_userid
     *
     * @return the value of sys_user.sys_userid
     *
     * @mbg.generated
     */
    public Integer getSysUserid() {
        return sysUserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.sys_userid
     *
     * @param sysUserid the value for sys_user.sys_userid
     *
     * @mbg.generated
     */
    public void setSysUserid(Integer sysUserid) {
        this.sysUserid = sysUserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.sys_username
     *
     * @return the value of sys_user.sys_username
     *
     * @mbg.generated
     */
    public String getSysUsername() {
        return sysUsername;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.sys_username
     *
     * @param sysUsername the value for sys_user.sys_username
     *
     * @mbg.generated
     */
    public void setSysUsername(String sysUsername) {
        this.sysUsername = sysUsername == null ? null : sysUsername.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.sys_account
     *
     * @return the value of sys_user.sys_account
     *
     * @mbg.generated
     */
    public String getSysAccount() {
        return sysAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.sys_account
     *
     * @param sysAccount the value for sys_user.sys_account
     *
     * @mbg.generated
     */
    public void setSysAccount(String sysAccount) {
        this.sysAccount = sysAccount == null ? null : sysAccount.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.sys_password
     *
     * @return the value of sys_user.sys_password
     *
     * @mbg.generated
     */
    public String getSysPassword() {
        return sysPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.sys_password
     *
     * @param sysPassword the value for sys_user.sys_password
     *
     * @mbg.generated
     */
    public void setSysPassword(String sysPassword) {
        this.sysPassword = sysPassword == null ? null : sysPassword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.sys_nickname
     *
     * @return the value of sys_user.sys_nickname
     *
     * @mbg.generated
     */
    public String getSysNickname() {
        return sysNickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.sys_nickname
     *
     * @param sysNickname the value for sys_user.sys_nickname
     *
     * @mbg.generated
     */
    public void setSysNickname(String sysNickname) {
        this.sysNickname = sysNickname == null ? null : sysNickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.sys_headimg
     *
     * @return the value of sys_user.sys_headimg
     *
     * @mbg.generated
     */
    public String getSysHeadimg() {
        return sysHeadimg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.sys_headimg
     *
     * @param sysHeadimg the value for sys_user.sys_headimg
     *
     * @mbg.generated
     */
    public void setSysHeadimg(String sysHeadimg) {
        this.sysHeadimg = sysHeadimg == null ? null : sysHeadimg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.sys_register_time
     *
     * @return the value of sys_user.sys_register_time
     *
     * @mbg.generated
     */
    public Date getSysRegisterTime() {
        return sysRegisterTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.sys_register_time
     *
     * @param sysRegisterTime the value for sys_user.sys_register_time
     *
     * @mbg.generated
     */
    public void setSysRegisterTime(Date sysRegisterTime) {
        this.sysRegisterTime = sysRegisterTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.sys_last_modify_time
     *
     * @return the value of sys_user.sys_last_modify_time
     *
     * @mbg.generated
     */
    public Date getSysLastModifyTime() {
        return sysLastModifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.sys_last_modify_time
     *
     * @param sysLastModifyTime the value for sys_user.sys_last_modify_time
     *
     * @mbg.generated
     */
    public void setSysLastModifyTime(Date sysLastModifyTime) {
        this.sysLastModifyTime = sysLastModifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.sys_phonenumber
     *
     * @return the value of sys_user.sys_phonenumber
     *
     * @mbg.generated
     */
    public String getSysPhonenumber() {
        return sysPhonenumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.sys_phonenumber
     *
     * @param sysPhonenumber the value for sys_user.sys_phonenumber
     *
     * @mbg.generated
     */
    public void setSysPhonenumber(String sysPhonenumber) {
        this.sysPhonenumber = sysPhonenumber == null ? null : sysPhonenumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.sys_last_login_time
     *
     * @return the value of sys_user.sys_last_login_time
     *
     * @mbg.generated
     */
    public Date getSysLastLoginTime() {
        return sysLastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.sys_last_login_time
     *
     * @param sysLastLoginTime the value for sys_user.sys_last_login_time
     *
     * @mbg.generated
     */
    public void setSysLastLoginTime(Date sysLastLoginTime) {
        this.sysLastLoginTime = sysLastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.user_group_id
     *
     * @return the value of sys_user.user_group_id
     *
     * @mbg.generated
     */
    public Integer getUserGroupId() {
        return userGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.user_group_id
     *
     * @param userGroupId the value for sys_user.user_group_id
     *
     * @mbg.generated
     */
    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.sys_user_level
     *
     * @return the value of sys_user.sys_user_level
     *
     * @mbg.generated
     */
    public String getSysUserLevel() {
        return sysUserLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.sys_user_level
     *
     * @param sysUserLevel the value for sys_user.sys_user_level
     *
     * @mbg.generated
     */
    public void setSysUserLevel(String sysUserLevel) {
        this.sysUserLevel = sysUserLevel == null ? null : sysUserLevel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.enabled
     *
     * @return the value of sys_user.enabled
     *
     * @mbg.generated
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.enabled
     *
     * @param enabled the value for sys_user.enabled
     *
     * @mbg.generated
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.create_by
     *
     * @return the value of sys_user.create_by
     *
     * @mbg.generated
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.create_by
     *
     * @param createBy the value for sys_user.create_by
     *
     * @mbg.generated
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.user_salt
     *
     * @return the value of sys_user.user_salt
     *
     * @mbg.generated
     */
    public String getUserSalt() {
        return userSalt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.user_salt
     *
     * @param userSalt the value for sys_user.user_salt
     *
     * @mbg.generated
     */
    public void setUserSalt(String userSalt) {
        this.userSalt = userSalt == null ? null : userSalt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user.role_id
     *
     * @return the value of sys_user.role_id
     *
     * @mbg.generated
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user.role_id
     *
     * @param roleId the value for sys_user.role_id
     *
     * @mbg.generated
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }


    @Override
    public String toString() {
        return "SysUser{" +
                "sysRole=" + sysRole +
                ", sysUserid=" + sysUserid +
                ", sysUsername='" + sysUsername + '\'' +
                ", sysAccount='" + sysAccount + '\'' +
                ", sysPassword='" + sysPassword + '\'' +
                ", sysNickname='" + sysNickname + '\'' +
                ", sysHeadimg='" + sysHeadimg + '\'' +
                ", sysRegisterTime=" + sysRegisterTime +
                ", sysLastModifyTime=" + sysLastModifyTime +
                ", sysPhonenumber='" + sysPhonenumber + '\'' +
                ", sysLastLoginTime=" + sysLastLoginTime +
                ", userGroupId=" + userGroupId +
                ", sysUserLevel='" + sysUserLevel + '\'' +
                ", enabled=" + enabled +
                ", createBy=" + createBy +
                ", userSalt='" + userSalt + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}