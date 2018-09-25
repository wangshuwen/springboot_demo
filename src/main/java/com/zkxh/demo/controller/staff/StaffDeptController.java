package com.zkxh.demo.controller.staff;

import com.zkxh.demo.common.base.controller.impl.BaseController;
import com.zkxh.demo.common.enums.ResultEnum;
import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.service.staff.dept.StaffDeptService;
import com.zkxh.demo.vo.req.StaffDeptVoReq;
import com.zkxh.demo.vo.resp.StaffDeptVoResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName StaffDeptController
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/25 11:03
 * @Vserion v0.0.1
 */
@RestController
@Api(value = "StaffDeptController", tags = "矿下员工部门操作接口")
public class StaffDeptController extends BaseController {

    @Resource
    private StaffDeptService staffDeptService;

    @ApiOperation(value = "获取矿下员工的所有部门信息", notes = "获取部门信息，便于整合")
    @GetMapping("staff/dept/getDepts")
    public String getAllStaffDept() {

        List<StaffDeptVoResp> list = staffDeptService.getAllDept();
        if (list.size() > 0) {
            return ResultUtil.jsonToStringSuccess(list);
        } else {
            return ResultUtil.jsonToStringError(ResultEnum.FAILED);
        }
    }

    @ApiOperation(value = "录入部门信息", notes = "新增矿下部门信息")
    @PostMapping("staff/dept/addDept")
    public String getAllStaffDept(@RequestBody StaffDeptVoReq staddDeptVoReq) {
        Integer res = staffDeptService.addStaffDept(staddDeptVoReq);
        if (res == 1) {
            return ResultUtil.jsonToStringSuccess();
        } else {
            return ResultUtil.jsonToStringError(ResultEnum.FAILED);
        }
    }

    @ApiOperation(value = "更新部门信息", notes = "更新矿下部门信息")
    @PutMapping("staff/dept/updateDept")
    public String updateStaffDept(@RequestBody StaffDeptVoReq staffDeptVoReq) {
        Integer res = staffDeptService.updateStaffDept(staffDeptVoReq);
        if (res == 1) {
            return ResultUtil.jsonToStringSuccess();
        } else {
            return ResultUtil.jsonToStringError(ResultEnum.FAILED);
        }
    }


    @Transactional
    @ApiOperation(value = "删除部门信息", notes = "接口可以批量删除部门信息，也可单一删除，参数为List")
    @DeleteMapping("staff/dept/deleteDepts")
    public String deleteStaffDept(@RequestParam List<Integer> ids) {

        int len = ids.size();
        if (ids.size() > 0) {
            Integer res = staffDeptService.deleteStaffDeptById(ids);
            if (res == len) {
                return ResultUtil.jsonToStringSuccess();
            } else {
                return ResultUtil.jsonToStringError(ResultEnum.FAILED);
            }
        } else {
            return ResultUtil.jsonToStringError(ResultEnum.FAILED);
        }

    }


}
