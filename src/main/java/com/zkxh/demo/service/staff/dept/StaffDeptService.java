package com.zkxh.demo.service.staff.dept;

import com.github.pagehelper.Page;
import com.zkxh.demo.vo.req.StaffDeptVoReq;
import com.zkxh.demo.vo.resp.StaffDeptVoResp;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @ClassName StaffDeptService
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/25 13:41
 * @Vserion v0.0.1
 */

public interface StaffDeptService {

    Page getAllStaffDept(Integer startPage, Integer pageSize);

    Integer addStaffDept(StaffDeptVoReq staddDeptVoReq);

    Integer updateStaffDept(StaffDeptVoReq staffDeptVoReq);

    Integer deleteStaffDeptById(Integer[] ids);

    Page getAllStaffDeptByParams(Integer startPage, Integer pageSize, StaffDeptVoReq staffDeptVoReq);

    boolean checkDeptIsNotIncludeGroup(Integer deptId);
}
