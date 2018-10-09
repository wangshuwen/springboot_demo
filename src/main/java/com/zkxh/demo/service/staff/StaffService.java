package com.zkxh.demo.service.staff;

import com.zkxh.demo.model.staff.Staff;
import com.zkxh.demo.vo.req.StaffReqVo;
import com.zkxh.demo.vo.resp.StaffDeptVoResp;
import com.zkxh.demo.vo.resp.StaffInfo;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @ClassName StaffService
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/25 10:23
 * @Vserion v0.0.1
 */

public interface StaffService {


    int addStaff(StaffReqVo staffReqVo);


    int deleteStaddByIds(Integer[] ids);

    int updateStaffInfo(StaffReqVo staffVo);

    String getStaffInfoByStaff(StaffReqVo staffVo, Integer startPage, Integer pageSize);
}
