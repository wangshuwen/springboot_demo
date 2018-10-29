package com.zkxh.demo.controller.staff;

import com.zkxh.demo.common.base.controller.impl.BaseController;
import com.zkxh.demo.common.enums.ResultEnum;
import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.service.staff.group.StaffGroupService;
import com.zkxh.demo.vo.req.StaffGroupReqVo;
import com.zkxh.demo.vo.resp.StaffGroupRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName StaffGroupController
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/25 11:03
 * @Vserion v0.0.1
 */

@Api(value = "StaffGroupController", tags = "矿下员工组别操作接口")
@RestController
public class StaffGroupController extends BaseController {


    @Resource
    private StaffGroupService staffGroupService;

    @PostMapping("staff/group/addGroup")
    @ApiOperation(value = "添加员工组别", notes = "添加员工的工作组")
    public String addStaffGroup(@RequestBody StaffGroupReqVo staffGroupReqVo) {
        Integer res = staffGroupService.addStaffGroup(staffGroupReqVo);
        return res == 1 ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.FAILED);
    }


    @PutMapping("staff/group/updateGroup")
    @ApiOperation(value = "修改员工组别", notes = "修改员工的工作组信息")
    public String updateStaffGroup(@RequestBody StaffGroupReqVo staffGroupReqVo) {
        Integer res = staffGroupService.updateStaffGroup(staffGroupReqVo);
        return res == 1 ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.FAILED);
    }

    @Transactional
    @DeleteMapping("staff/group/deleteGroups")
    @ApiOperation(value = "删除员工组别", notes = "删除员工的工作组信息，支持批量删除")
    public String deleteStaffGroup(@RequestParam Integer[] ids) {
        int len = ids.length;
        if (len == 0)
            return ResultUtil.jsonToStringError(ResultEnum.REQUEST_DATA_IS_NULL);
        Integer res = staffGroupService.deleteStaffGroupsByGroupId(ids);
        return res == len ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.FAILED);
    }

    @GetMapping("staff/group/getGroupInfoByStaffDeptId")
    @ApiOperation(value = "通过员工所在的部门信息获取响应的所有的组别信息", notes = "通过的DeptId完成级联查询")
    @ApiImplicitParam(name = "staffDeptId", value = "部门ID")
    public String getStaffGroupByStaffDeptId(@RequestParam(name = "staffDeptId") Integer staffDeptId) {
        System.out.println(staffDeptId);
        List<StaffGroupRespVo> res = staffGroupService.getStaffGroupByDeptId(staffDeptId);
        return res.size() > 0 ? ResultUtil.jsonToStringSuccess(res) : ResultUtil.jsonToStringError(ResultEnum.DATA_NOT_FOUND);
    }

    @GetMapping("staff/group/getAllGroupInfo")
    @ApiOperation(value = "通过所有员工的组别信息", notes = "获取所有组别信息")
    @ApiImplicitParam(name = "staffDeptId", value = "部门ID")
    public String getAllGroupInfo() {
        List<StaffGroupRespVo> res = staffGroupService.getAllStaffGroupInfo();
        return res.size() > 0 ? ResultUtil.jsonToStringSuccess(res) : ResultUtil.jsonToStringError(ResultEnum.DATA_NOT_FOUND);
    }

}
