package com.zkxh.demo.service.staff;

import com.zkxh.demo.model.staff.Staff;
import com.zkxh.demo.vo.req.StaffInfoVO;
import com.zkxh.demo.vo.req.StaffReqVo;
import com.zkxh.demo.vo.resp.GasWSRespVO;
import com.zkxh.demo.vo.resp.StaffDeptVoResp;
import com.zkxh.demo.vo.resp.StaffInfo;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;

/**
 * @ClassName StaffService
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/25 10:23
 * @Vserion v0.0.1
 */

public interface StaffService {


    int addStaff(StaffInfoVO staffInfoVO);

    int deleteStaffByIds(Integer[] ids);

    int updateStaffInfo(StaffInfoVO staffInfoVO);

    String getStaffInfoByStaff(StaffReqVo staffVo, Integer startPage, Integer pageSize);

    GasWSRespVO findStaffNameByTerminalId(Integer terminalId);

    Map<String, Object> findStaffGroupAndDeptByStaffId(Integer staffId);

    Map<String, Object> findStaffIdByTerminalId(Integer terminalId);

}
