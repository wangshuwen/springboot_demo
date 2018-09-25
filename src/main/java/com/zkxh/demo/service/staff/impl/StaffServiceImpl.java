package com.zkxh.demo.service.staff.impl;

import com.zkxh.demo.common.util.convert.DateConvert;
import com.zkxh.demo.dao.staff.StaffMapper;
import com.zkxh.demo.model.staff.Staff;
import com.zkxh.demo.service.staff.StaffService;
import com.zkxh.demo.vo.req.StaffReqVo;
import com.zkxh.demo.vo.resp.StaffDeptVoResp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName StaffServiceImpl
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/25 10:24
 * @Vserion v0.0.1
 */
@Service
public class StaffServiceImpl implements StaffService {


    @Resource
    private StaffMapper staffMapper;

    @Override
    public int addStaff(StaffReqVo staffReqVo) {

        Staff staff = new Staff();

        staff.setCreateTime(new Date());
        staff.setGroupId(staffReqVo.getGroupId());
        staff.setIsPerson(staffReqVo.getIsPerson());
        staff.setStaffAddress(staffReqVo.getStaffAddress());
        staff.setStaffBirthday(staffReqVo.getStaffBirthday());
        staff.setStaffEmail(staffReqVo.getStaffEmail());
        staff.setStaffIdCard(staffReqVo.getStaffIdCard());
        staff.setStaffJobId(staffReqVo.getStaffJobId());
        staff.setStaffName(staffReqVo.getStaffName());
        staff.setStaffNativePlace(staffReqVo.getStaffNativePlace());
        staff.setStaffNumber(staffReqVo.getStaffNumber());
        staff.setStaffPhone(staffReqVo.getStaffPhone());
        staff.setStaffWedlock(staffReqVo.getStaffWedlock());
        staff.setStaffTypeId(staffReqVo.getStaffTypeId());
        staff.setStaffSex(staffReqVo.getStaffSex());

        return staffMapper.insert(staff);
    }


}
