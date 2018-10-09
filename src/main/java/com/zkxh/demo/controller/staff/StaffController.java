package com.zkxh.demo.controller.staff;

import com.zkxh.demo.common.enums.ResultEnum;
import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.model.staff.Staff;
import com.zkxh.demo.model.staff.StaffDept;
import com.zkxh.demo.service.staff.StaffService;
import com.zkxh.demo.service.staff.dept.StaffDeptService;
import com.zkxh.demo.vo.req.StaffReqVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName StaffController
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/25 10:07
 * @Vserion v0.0.1
 */
@RestController
@Api(value = "StaffController", tags = {"矿下员工信息操作接口"})
public class StaffController {


    @Resource
    private StaffService staffService;


    @PostMapping("staff/addStaff")
    @ApiOperation(value = "矿下员工信息录入", notes = "对员工信息（必要信息录入）通过页面输入信息")
    public String addStaffInfo(@RequestBody StaffReqVo staffReqVo) {

        int res = staffService.addStaff(staffReqVo);

        if (res == 1) {
            return ResultUtil.jsonToStringSuccess();
        } else {
            return ResultUtil.jsonToStringError(ResultEnum.FAILED);
        }

    }

    @PostMapping("staff/addStaffs")
    @ApiOperation(value = "解析Excel表格实现批量矿下员工信息录入", notes = "批量导入员工信息")
    public String addStaffInfo(@RequestBody MultipartFile staffInfoFile) {
        //TODO 解析Excel表格
        return ResultUtil.jsonToStringSuccess();
    }

    @Transactional
    @DeleteMapping("staff/delStaff")
    @ApiOperation(value = "根据员工ID删除员工的基础信息", notes = "可以批量或单一删除信息，参数为数组形式")
    public String deleteStaffByIds(@RequestParam Integer[] ids) {
        int len = ids.length;
        int res = staffService.deleteStaddByIds(ids);
        if (len == res) {
            return ResultUtil.jsonToStringSuccess();
        } else {
            return ResultUtil.jsonToStringError(ResultEnum.FAILED);
        }
    }

    @PutMapping("staff/updateStaff")
    @ApiOperation(value = "更新员工的基础信息", notes = "通过员工的ID更新信息")
    public String updateStaffInfo(@RequestParam StaffReqVo staffVo) {
        int res = staffService.updateStaffInfo(staffVo);
        if (res == 1) {
            return ResultUtil.jsonToStringSuccess();
        } else {
            return ResultUtil.jsonToStringError(ResultEnum.FAILED);
        }
    }

    @GetMapping("staff/getStaffByPage")
    @ApiOperation(value = "获取员工基础信息", notes = "获取员工的信息，模糊查询")
    public String getAllStaffInfoByPage(@RequestParam(required = false, defaultValue = "8") Integer pageSize, @RequestParam(required = false, defaultValue = "1", name = "startPage") Integer startPage, @RequestParam(name = "staffId", required = false) Integer staffId, @RequestParam(name = "staffName", required = false) String staffName) {


        StaffReqVo staffReqVo = new StaffReqVo();
        if (staffName != null) {
            staffReqVo.setStaffName(staffName);
        }
        if (staffName != null) {
            staffReqVo.setStaffId(staffId);
        }

        String staffList = staffService.getStaffInfoByStaff(staffReqVo, startPage, pageSize);

        return staffList;
    }

    @GetMapping("staff/getStaff")
    @ApiOperation(value = "获取员工基础信息分页", notes = "获取员工的信息，实现分页查询，模糊查询")
    public String getAllStaffInfo(@RequestParam String name, @RequestParam(name = "pageSize", required = false, defaultValue = "8") Integer pageSize, @RequestParam(required = false, defaultValue = "1", name = "startPage") Integer startPage) {
        StaffReqVo staffReqVo = new StaffReqVo();
//        staffReqVo.setStaffName(1);
//        List<Staff> staffList = staffService.getStaffInfoByStaff(1);

        return ResultUtil.jsonToStringSuccess();
    }


}