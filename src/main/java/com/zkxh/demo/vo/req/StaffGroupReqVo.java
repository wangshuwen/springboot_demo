package com.zkxh.demo.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName StaffGroupReqVo
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/25 14:19
 * @Vserion v0.0.1
 */
@ApiModel(value = "StaffGroupReqVo", description = "矿下人员组别基本信息")
public class StaffGroupReqVo {

    @ApiModelProperty(name = "groupId", value = "组别ID", hidden = true)
    private Integer groupId;

    @ApiModelProperty(name = "groupName", value = "组别名称", required = true)
    private String groupName;

    @ApiModelProperty(name = "deptId", value = "所属部门ID", required = true)
    private Integer deptId;


    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}
