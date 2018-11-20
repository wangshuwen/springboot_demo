package com.zkxh.demo.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @ClassName StaffInfoVO
 * @Description
 * @Auther lifeng
 * @DATE 2018/10/30 15:28
 * @Vserion v0.0.1
 */
@ApiModel(value = "StaffInfoVO", description = "矿下员工基本信息对象")
public class StaffInfoVO {

    private Integer deptId;
    private Integer staffId;
    private Integer terminalId;

    @ApiModelProperty(name = "staffName", value = "员工姓名", required = true)
    private String staffName;

    @ApiModelProperty(name = "staffSex", value = "员工性别", required = true)
    private Integer staffSex;

    @ApiModelProperty(name = "staffBirthday", value = "生日")
    private Date staffBirthday;

    @ApiModelProperty(name = "staffIdCard", value = "身份证", required = true)
    private String staffIdCard;

    @ApiModelProperty(name = "staffPhone", value = "联系电话", required = true)
    private String staffPhone;

    @ApiModelProperty(name = "jobId", value = "工作内容等级(关联staff_job_id)", required = true)
    private Integer jobId;

    @ApiModelProperty(name = "isPerson", value = " 是否为人员（0 职工，1领导，2外来人，3物品）", required = true)
    private Integer isPerson;

    @ApiModelProperty(name = "createTime", value = "创建时间", required = true)
    private Date createTime;

    @ApiModelProperty(name = "groupId", value = "所属组别", required = true)
    private Integer groupId;

    private String groupName;

    private String deptName;

    private String jobName;


    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Integer terminalId) {
        this.terminalId = terminalId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Integer getStaffSex() {
        return staffSex;
    }

    public void setStaffSex(Integer staffSex) {
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
        this.staffIdCard = staffIdCard;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getIsPerson() {
        return isPerson;
    }

    public void setIsPerson(Integer isPerson) {
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
