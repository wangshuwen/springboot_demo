package com.zkxh.demo.service.staff.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.dao.staff.StaffMapper;
import com.zkxh.demo.dao.terminal.StaffTerminalMapper;
import com.zkxh.demo.dto.staff.StaffInfoDto;
import com.zkxh.demo.model.staff.Staff;
import com.zkxh.demo.model.terminal.StaffTerminal;
import com.zkxh.demo.service.staff.StaffService;
import com.zkxh.demo.vo.req.StaffInfoVO;
import com.zkxh.demo.vo.req.StaffReqVo;
import com.zkxh.demo.vo.resp.GasWSRespVO;
import com.zkxh.demo.vo.resp.StaffInfo;
import org.apache.ibatis.annotations.Param;
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
    public int addStaff(StaffInfoVO staffInfoVO) {

        Staff staff = new Staff();

        staff.setCreateTime(new Date());
        staff.setGroupId(staffInfoVO.getGroupId());
        staff.setIsPerson(staffInfoVO.getIsPerson());
        staff.setStaffBirthday(staffInfoVO.getStaffBirthday());
        staff.setStaffIdCard(staffInfoVO.getStaffIdCard());
        staff.setStaffJobId(staffInfoVO.getJobId());
        staff.setStaffName(staffInfoVO.getStaffName());
        staff.setStaffPhone(staffInfoVO.getStaffPhone());
        staff.setStaffSex(staffInfoVO.getStaffSex());

        int result = staffMapper.insertSelective(staff);

        if (result == 1) {
            if (staffInfoVO.getTerminalId() != null && staffInfoVO.getTerminalId() > 0) {
                staffTerminalMapper.updateTerminalBinding(staff.getStaffId(), staffInfoVO.getTerminalId());
            }
            return 1;
        } else {
            return 0;
        }
    }


    @Override
    public int deleteStaffByIds(Integer[] ids) {
        int count = 0;
        for (Integer id : ids) {

            int result_b = staffMapper.deleteByPrimaryKey(id);
            boolean flag = staffTerminalMapper.countTerminalNumByStaffId(id);
//            int result_a = 0;
            if (flag == true)
                staffTerminalMapper.updateTerminalUnBindingByStaffId(id);
            if (result_b == 1)
                count++;
        }
        return count;
    }

    @Override
    public int updateStaffInfo(StaffInfoVO staffInfoVO) {

        Staff staff = new Staff();

        staff.setStaffName(staffInfoVO.getStaffName());

        staff.setStaffId(staffInfoVO.getStaffId());

        staff.setStaffSex(staffInfoVO.getStaffSex());

        staff.setStaffIdCard(staffInfoVO.getStaffIdCard());

        staff.setStaffBirthday(staffInfoVO.getStaffBirthday());

        staff.setGroupId(staffInfoVO.getGroupId());

        staff.setStaffPhone(staffInfoVO.getStaffPhone());

        staff.setStaffJobId(staffInfoVO.getJobId());

        staff.setStaffTypeId(staffInfoVO.getTerminalId());

        //TODO  更新员工信息
        int res = staffMapper.updateByPrimaryKeySelective(staff);

        if (staffInfoVO.getTerminalId() != null) {
            staffTerminalMapper.updateTerminalBinding(staffInfoVO.getStaffId(), staffInfoVO.getTerminalId());
        }

        return res;
    }

    @Override
    public String getStaffInfoByStaff(StaffReqVo staffVo, Integer startPage, Integer pageSize) {
        Staff staff = new Staff();
        if (staffVo.getStaffName() != null) {
            staff.setStaffName(staffVo.getStaffName());
        }

        Page page = PageHelper.startPage(startPage, pageSize);
        List<StaffInfoDto> staffInfoDTOs = staffMapper.selectStaffByParams(staff);

        List<StaffInfoVO> list = Collections.synchronizedList(new ArrayList<>());
        StaffInfoVO staffInfoVO = null;
        for (StaffInfoDto item : staffInfoDTOs) {
            staffInfoVO = new StaffInfoVO();

            staffInfoVO.setStaffId(item.getStaffId());
            staffInfoVO.setCreateTime(item.getCreateTime());
            staffInfoVO.setStaffBirthday(item.getStaffBirthday());
            staffInfoVO.setGroupId(item.getGroupId());
            staffInfoVO.setGroupName(item.getGroupName());
            staffInfoVO.setDeptName(item.getDeptName());
            staffInfoVO.setStaffName(item.getStaffName());
            staffInfoVO.setIsPerson(item.getIsPerson());
            staffInfoVO.setStaffBirthday(item.getStaffBirthday());

            staffInfoVO.setStaffPhone(item.getStaffPhone());
            staffInfoVO.setStaffSex(item.getStaffSex());
            staffInfoVO.setStaffIdCard(item.getStaffIdCard());
            staffInfoVO.setJobId(item.getJobId());
            staffInfoVO.setJobName(item.getJobName());
            staffInfoVO.setTerminalId(item.getTerminalId());
            staffInfoVO.setDeptId(item.getDeptId());

            list.add(staffInfoVO);
        }

        PageInfo pageInfo = new PageInfo<>(page);

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
