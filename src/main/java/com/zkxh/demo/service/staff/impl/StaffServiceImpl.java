package com.zkxh.demo.service.staff.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.dao.staff.StaffMapper;
import com.zkxh.demo.dao.terminal.StaffTerminalMapper;
import com.zkxh.demo.dto.staff.StaffInfoDto;
import com.zkxh.demo.model.staff.Staff;
import com.zkxh.demo.service.staff.StaffService;
import com.zkxh.demo.vo.req.StaffReqVo;
import com.zkxh.demo.vo.resp.GasWSRespVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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

    @Resource
    private StaffTerminalMapper staffTerminalMapper;

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

        return staffMapper.insertSelective(staff);
    }


    @Override
    public int deleteStaffByIds(Integer[] ids) {
        int count = 0;
        for (Integer id : ids) {
            staffMapper.deleteByPrimaryKey(id);
            count++;
        }
        return count;
    }

    @Override
    public int updateStaffInfo(StaffReqVo staffVo) {

        Staff staff = new Staff();

        staff.setStaffPhone(staffVo.getStaffPhone());

        staff.setStaffJobId(staffVo.getStaffJobId());

        staff.setStaffTypeId(staffVo.getStaffTypeId());

        //TODO  更新员工信息
        int res = staffMapper.updateByPrimaryKeySelective(staff);

        return res;
    }

    @Override
    public String getStaffInfoByStaff(StaffReqVo staffVo, Integer startPage, Integer pageSize) {
        Staff staff = new Staff();
        if (staffVo.getStaffId() != null) {
            staff.setStaffId(staffVo.getStaffId());
        }
        if (staffVo.getStaffName() != null) {
            staff.setStaffName(staffVo.getStaffName());
        }
        PageHelper.startPage(startPage, pageSize);
        List<StaffInfoDto> staffInfoDtos = staffMapper.selectStaffByParams(staff);
        PageInfo<StaffInfoDto> pageInfo = new PageInfo<>(staffInfoDtos);

        return ResultUtil.jsonToStringSuccess(pageInfo);
    }

    @Override
    public GasWSRespVO findStaffNameByTerminalId(Integer terminalId) {

        List<Map<String, Object>> resultMap = staffTerminalMapper.selectStaffNameByTerminalId(terminalId);

        GasWSRespVO gasWSRespVO = new GasWSRespVO();
        if (resultMap.size() > 0) {
            Integer staffId = (Integer) resultMap.get(0).get("staff_id");
            String staffName = (String) resultMap.get(0).get("staff_name");
            gasWSRespVO.setStaffName(staffName);
            gasWSRespVO.setStaffId(staffId);
            return gasWSRespVO;
        } else {
            gasWSRespVO.setStaffName("未知人员");
            gasWSRespVO.setStaffId(0);
            return gasWSRespVO;
        }
    }

    @Override
    public Map<String, Object> findStaffGroupAndDeptByStaffId(Integer staffId) {
        return staffMapper.selectGroupAndDeptByStaffId(staffId);
    }

    @Override
    public Map<String, Object> findStaffIdByTerminalId(Integer terminalId) {
        return staffMapper.selectStaffInfoByTerminalId(terminalId);
    }


}
