package com.zkxh.demo.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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


    public StaffDeptVoResp() {
    }

    public StaffDeptVoResp(Integer deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
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
