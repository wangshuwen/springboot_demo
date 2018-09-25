package com.zkxh.demo.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @ClassName StaffReqVo
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/25 10:37
 * @Vserion v0.0.1
 */
@ApiModel(value = "StaffReqVo", description = "矿下员工基本信息对象")
public class StaffReqVo {

    @ApiModelProperty(name = "staffId", value = "员工ID", hidden = true)
    private Integer staffId;

    @ApiModelProperty(name = "staffNumber", value = "员工编号")
    private String staffNumber;

    @ApiModelProperty(name = "staffName", value = "员工姓名", required = true)
    private String staffName;

    @ApiModelProperty(name = "staffSex", value = "员工性别", required = true)
    private Boolean staffSex;

    @ApiModelProperty(name = "staffBirthday", value = "员工性别")
    private Date staffBirthday;

    @ApiModelProperty(name = "staffIdCard", value = "身份证", required = true)
    private String staffIdCard;

    @ApiModelProperty(name = "staffWedlock", value = "备用字段")
    private String staffWedlock;

    @ApiModelProperty(name = "staffEmail", value = "邮箱")
    private String staffEmail;

    @ApiModelProperty(name = "staffAddress", value = "地址", required = true)
    private String staffAddress;

    @ApiModelProperty(name = "staffPhone", value = "联系电话", required = true)
    private String staffPhone;

    @ApiModelProperty(name = "staffJobId", value = "工作内容等级(关联staff_job_id)", required = true)
    private Integer staffJobId;

    @ApiModelProperty(name = "staffNativePlace", value = "户籍地址")
    private String staffNativePlace;

    @ApiModelProperty(name = "staffTypeId", value = "职工类型", required = true)
    private Integer staffTypeId;


    @ApiModelProperty(name = "isPerson", value = " 是否为人员（0 职工，1领导，2外来人，3物品）", required = true)
    private Boolean isPerson;

    @ApiModelProperty(hidden = true, name = "createTime", value = "创建时间", required = true)
    private Date createTime;


    @ApiModelProperty(name = "groupId", value = "所属组别", required = true)
    private Integer groupId;


    public Integer getStaffId() {
        return staffId;
    }


    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }


    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber == null ? null : staffNumber.trim();
    }


    public String getStaffName() {
        return staffName;
    }


    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }


    public Boolean getStaffSex() {
        return staffSex;
    }


    public void setStaffSex(Boolean staffSex) {
        this.staffSex = staffSex;
    }


    public Date getStaffBirthday() {
        return staffBirthday;
    }


    public void setStaffBirthday(Date staffBirthday) {
        this.staffBirthday = staffBirthday;
    }


    public String getStaffIdCard() {
        return staffIdCard;
    }


    public void setStaffIdCard(String staffIdCard) {
        this.staffIdCard = staffIdCard == null ? null : staffIdCard.trim();
    }

    public String getStaffWedlock() {
        return staffWedlock;
    }


    public void setStaffWedlock(String staffWedlock) {
        this.staffWedlock = staffWedlock == null ? null : staffWedlock.trim();
    }

    public String getStaffEmail() {
        return staffEmail;
    }


    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail == null ? null : staffEmail.trim();
    }

    public String getStaffAddress() {
        return staffAddress;
    }


    public void setStaffAddress(String staffAddress) {
        this.staffAddress = staffAddress == null ? null : staffAddress.trim();
    }

    public String getStaffPhone() {
        return staffPhone;
    }


    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone == null ? null : staffPhone.trim();
    }

    public Integer getStaffJobId() {
        return staffJobId;
    }

    public void setStaffJobId(Integer staffJobId) {
        this.staffJobId = staffJobId;
    }


    public String getStaffNativePlace() {
        return staffNativePlace;
    }


    public void setStaffNativePlace(String staffNativePlace) {
        this.staffNativePlace = staffNativePlace == null ? null : staffNativePlace.trim();
    }

    public Integer getStaffTypeId() {
        return staffTypeId;
    }


    public void setStaffTypeId(Integer staffTypeId) {
        this.staffTypeId = staffTypeId;
    }


    public Boolean getIsPerson() {
        return isPerson;
    }

    public void setIsPerson(Boolean isPerson) {
        this.isPerson = isPerson;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Integer getGroupId() {
        return groupId;
    }


    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
