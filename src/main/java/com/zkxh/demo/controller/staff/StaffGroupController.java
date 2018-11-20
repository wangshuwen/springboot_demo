package com.zkxh.demo.controller.staff;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zkxh.demo.common.base.controller.impl.BaseController;
import com.zkxh.demo.common.enums.ResultEnum;
import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.service.staff.group.StaffGroupService;
import com.zkxh.demo.vo.req.StaffGroupReqVo;
import com.zkxh.demo.vo.req.StaffInfoVO;
import com.zkxh.demo.vo.resp.StaffGroupRespVo;
import com.zkxh.demo.vo.resp.StaffInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
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
    public String getStaffGroupByStaffDeptId(@RequestParam(name = "limit", required = false, defaultValue = "8") Integer pageSize,
                                             @RequestParam(name = "page", required = false, defaultValue = "1") Integer startPage,
                                             @RequestParam(name = "staffDeptId", required = false) Integer staffDeptId) {
        Page res = null;
        if (null == staffDeptId || 0 == staffDeptId) {
            res = staffGroupService.getAllStaffGroupInfo(startPage, pageSize);
        } else {
            res = staffGroupService.getStaffGroupByDeptId(startPage, pageSize, staffDeptId);
        }
        PageInfo pageInfo = new PageInfo(res);
        return res.size() > 0 ? ResultUtil.jsonToStringSuccess(pageInfo) : ResultUtil.jsonToStringError(ResultEnum.DATA_NOT_FOUND);
    }

    @GetMapping("staff/group/getAllGroupInfo")
    @ApiOperation(value = "通过所有员工的组别信息", notes = "获取所有组别信息")
    @ApiImplicitParam(name = "staffDeptId", value = "部门ID")
    public String getAllGroupInfo(@RequestParam(name = "limit", required = false, defaultValue = "8") Integer pageSize
            , @RequestParam(name = "page", required = false, defaultValue = "1") Integer startPage) {
        Page res = staffGroupService.getAllStaffGroupInfo(startPage, pageSize);
        PageInfo pageInfo = new PageInfo(res);
        return res.size() > 0 ? ResultUtil.jsonToStringSuccess(pageInfo) : ResultUtil.jsonToStringError(ResultEnum.DATA_NOT_FOUND);
    }

    @GetMapping("staff/group/getAllStaffByGroupId")
    @ApiOperation(value = "获取当前组别下所有员工信息", notes = "利用group ID 获取")
    @ApiImplicitParam(name = "groupId", value = "分组ID")
    public String getCurrentGroupStaff(@RequestParam(name = "groupId") Integer groupId, @RequestParam(name = "limit", defaultValue = "10", required = false) Integer pageSize, @RequestParam(name = "page", defaultValue = "1", required = false) Integer startPage) {
        Page list = staffGroupService.getStaffInfoByGroupId(groupId, startPage, pageSize);
        PageInfo pageInfo = new PageInfo<>(list);
        return ResultUtil.jsonToStringSuccess(pageInfo);
    }

}
