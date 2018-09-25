package com.zkxh.demo.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName StaffDeptVoReq
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/25 14:04
 * @Vserion v0.0.1
 */
@ApiModel(value = "StaffDeptVoReq", description = "矿下人员部门基本信息")
public class StaffDeptVoReq {

    @ApiModelProperty(name = "deptId", value = "部门ID ")
    private Integer deptId;
    @ApiModelProperty(name = "deptName", value = "部门名称 ", required = true)
    private String deptName;

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
