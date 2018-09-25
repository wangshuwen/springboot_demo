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
        if (res == 1) {
            return ResultUtil.jsonToStringSuccess();
        } else {
            return ResultUtil.jsonToStringError(ResultEnum.FAILED);
        }
    }


    @PutMapping("staff/group/updateGroup")
    @ApiOperation(value = "修改员工组别", notes = "修改员工的工作组信息")
    public String updateStaffGroup(@RequestBody StaffGroupReqVo staffGroupReqVo) {
        Integer res = staffGroupService.updateStaffGroup(staffGroupReqVo);
        if (res == 1) {
            return ResultUtil.jsonToStringSuccess();
        } else {
            return ResultUtil.jsonToStringError(ResultEnum.FAILED);
        }
    }


    @Transactional
    @DeleteMapping("staff/group/deleteGroups")
    @ApiOperation(value = "删除员工组别", notes = "删除员工的工作组信息，支持批量删除")
    public String deleteStaffGroup(@RequestParam List<Integer> ids) {

        int len = ids.size();

        Integer res = staffGroupService.deleteStaffGroupsByGroupId(ids);
        if (res == len) {
            return ResultUtil.jsonToStringSuccess();
        } else {
            return ResultUtil.jsonToStringError(ResultEnum.FAILED);
        }
    }


    @GetMapping("staff/group/getGroupInfoByStaffDeptId")
    @ApiOperation(value = "通过员工所在的部门信息获取响应的所有的组别信息", notes = "通过的DeptId完成级联查询")
    @ApiImplicitParam(name = "staffDeptId", value = "部门ID", required = true)
    public String getStaffGroupByStaffDeptId(@RequestParam Integer staffDeptId) {
        List<StaffGroupRespVo> res = staffGroupService.getStaffGroupByDeptId(staffDeptId);
        if (res.size() > 0) {
            return ResultUtil.jsonToStringSuccess(res);
        } else {
            return ResultUtil.jsonToStringError(ResultEnum.FAILED);
        }
    }


}
