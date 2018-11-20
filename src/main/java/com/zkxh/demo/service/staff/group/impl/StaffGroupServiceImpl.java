package com.zkxh.demo.service.staff.group.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zkxh.demo.common.enums.ResultEnum;
import com.zkxh.demo.common.handle.RuntimeServiceException;
import com.zkxh.demo.dao.staff.StaffGroupMapper;
import com.zkxh.demo.dao.staff.StaffMapper;
import com.zkxh.demo.dto.staff.StaffDto;
import com.zkxh.demo.dto.staff.StaffInfoDto;
import com.zkxh.demo.model.staff.Staff;
import com.zkxh.demo.model.staff.StaffGroup;
import com.zkxh.demo.model.staff.StaffGroupExample;
import com.zkxh.demo.service.staff.group.StaffGroupService;
import com.zkxh.demo.vo.req.StaffGroupReqVo;
import com.zkxh.demo.vo.req.StaffInfoVO;
import com.zkxh.demo.vo.resp.StaffGroupRespVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName StaffGroupServiceImpl
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/25 14:20
 * @Vserion v0.0.1
 */


@Service
public class StaffGroupServiceImpl implements StaffGroupService {


    @Resource
    private StaffGroupMapper staffGroupMapper;

    @Resource
    private StaffMapper staffMapper;

    @Override
    public Integer addStaffGroup(StaffGroupReqVo staffGroupReqVo) {
        StaffGroup staffGroup = new StaffGroup();

        staffGroup.setDeptId(staffGroupReqVo.getDeptId());
        staffGroup.setGroupName(staffGroupReqVo.getGroupName());

        return staffGroupMapper.insert(staffGroup);
    }

    @Override
    public Integer updateStaffGroup(StaffGroupReqVo staffGroupReqVo) {
        StaffGroup staffGroup = new StaffGroup();

        staffGroup.setDeptId(staffGroupReqVo.getDeptId());
        staffGroup.setGroupName(staffGroupReqVo.getGroupName());
        staffGroup.setGroupId(staffGroupReqVo.getGroupId());

        return staffGroupMapper.updateByPrimaryKey(staffGroup);
    }

    @Override
    public Integer deleteStaffGroupsByGroupId(Integer[] ids) {

        int count = 0;

        for (Integer id : ids) {
            Integer res = staffGroupMapper.countStaffNumByGroupId(id);
            if (res > 0)
                throw new RuntimeServiceException(ResultEnum.DATA_IS_NOT_NULL);
            staffGroupMapper.deleteByPrimaryKey(id);
            count++;

        }
        if (count == ids.length) {
            return count;
        } else {
            return 0;
        }
    }

    @Override
    public Page getStaffGroupByDeptId(Integer startPage, Integer pageSize, Integer staffDeptId) {

        Page page = PageHelper.startPage(startPage, pageSize);
        List<StaffGroup> staffGroupSList = staffGroupMapper.selectStaffGroupsByDeptId(staffDeptId);

        StaffGroupRespVo staffGroupRespVo = null;

        List<StaffGroupRespVo> list = Collections.synchronizedList(new ArrayList<>());
        for (StaffGroup group : staffGroupSList) {
            staffGroupRespVo = new StaffGroupRespVo();
            staffGroupRespVo.setGroupId(group.getGroupId());
            staffGroupRespVo.setGroupName(group.getGroupName());
            staffGroupRespVo.setDeptName(group.getStaffDept().getDeptName());
            staffGroupRespVo.setStaffList(group.getStaffList());
            list.add(staffGroupRespVo);
        }
        return page;
    }

    @Override
    public Page getAllStaffGroupInfo(Integer startPage, Integer pageSize) {

        Page page = PageHelper.startPage(startPage, pageSize);
        List<StaffGroup> staffGroupsList = staffGroupMapper.selectAllStaffGroups();
        StaffGroupRespVo staffGroupRespVo = null;
        List<StaffGroupRespVo> list = Collections.synchronizedList(new ArrayList<>());
        for (StaffGroup group : staffGroupsList) {
            staffGroupRespVo = new StaffGroupRespVo();
            staffGroupRespVo.setGroupId(group.getGroupId());
            staffGroupRespVo.setGroupName(group.getGroupName());
            staffGroupRespVo.setDeptName(group.getStaffDept().getDeptName());
            staffGroupRespVo.setDeptId(group.getStaffDept().getDeptId());
            staffGroupRespVo.setStaffList(group.getStaffList());
            list.add(staffGroupRespVo);
        }

        return page;
    }

    @Override
    public boolean checkGroupIsNotIncludeStaff(Integer groupId) {

        Integer res = staffGroupMapper.countStaffNumByGroupId(groupId);
        if (res > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Page getStaffInfoByGroupId(Integer groupId, Integer startPage, Integer pageSize) {


        Staff staff = new Staff();
        staff.setGroupId(groupId);

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
            staffInfoVO.setJobName(item.getJobName());
            staffInfoVO.setTerminalId(item.getTerminalId());
            staffInfoVO.setDeptId(item.getDeptId());

            list.add(staffInfoVO);
        }
        return page;
    }
}
