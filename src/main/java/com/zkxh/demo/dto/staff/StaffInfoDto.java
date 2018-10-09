package com.zkxh.demo.dto.staff;

import com.zkxh.demo.model.staff.StaffDept;
import com.zkxh.demo.model.staff.StaffGroup;
import com.zkxh.demo.model.staff.StaffJob;

/**
 * @ClassName StaffInfoDto
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/30 9:20
 * @Vserion v0.0.1
 */

public class StaffInfoDto {

    private StaffJob staffJob;

    private StaffDept staffDept;

    private StaffGroup staffGroup;


    private Integer staffId;

    private String staffName;

    private String staffNumber;

    private String staffPhone;

    private String groupName;

    private Integer groupId;

    private String deptName;

    private Integer deptId;

    private Integer isPerson;

    private Integer terminalId;

    private String jobName;


    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setIsPerson(Integer isPerson) {
        this.isPerson = isPerson;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getIsPerson() {
        return isPerson;
    }

    public void setIsPersion(Integer isPerson) {
        this.isPerson = isPerson;
    }

    public Integer getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Integer terminalId) {
        this.terminalId = terminalId;
    }
}
