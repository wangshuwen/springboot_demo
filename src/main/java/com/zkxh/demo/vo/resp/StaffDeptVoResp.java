package com.zkxh.demo.vo.resp;

import com.zkxh.demo.model.staff.Staff;
import com.zkxh.demo.model.staff.StaffGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName StaffDeptVoResp
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/25 13:37
 * @Vserion v0.0.1
 */
@ApiModel(value = "矿下部门信息对象")
public class StaffDeptVoResp {


    @ApiModelProperty(name = "deptId", value = "部门ID ")
    private Integer deptId;
    @ApiModelProperty(name = "deptName", value = "部门名称 ")
    private String deptName;

    private List<Staff> staffList;

    private List<StaffGroup> staffGroups;


    public StaffDeptVoResp() {
    }

    public StaffDeptVoResp(Integer deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public List<StaffGroup> getStaffGroups() {
        return staffGroups;
    }

    public void setStaffGroups(List<StaffGroup> staffGroups) {
        this.staffGroups = staffGroups;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
