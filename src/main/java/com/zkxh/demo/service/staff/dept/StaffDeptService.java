package com.zkxh.demo.service.staff.dept;

import com.zkxh.demo.vo.req.StaffDeptVoReq;
import com.zkxh.demo.vo.resp.StaffDeptVoResp;

import java.util.List;

/**
 * @ClassName StaffDeptService
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/25 13:41
 * @Vserion v0.0.1
 */

public interface StaffDeptService {

    List<StaffDeptVoResp> getAllDept();

    Integer addStaffDept(StaffDeptVoReq staddDeptVoReq);

    Integer updateStaffDept(StaffDeptVoReq staffDeptVoReq);

    Integer deleteStaffDeptById(List<Integer> ids);
}
