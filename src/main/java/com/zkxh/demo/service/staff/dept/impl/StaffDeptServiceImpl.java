package com.zkxh.demo.service.staff.dept.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zkxh.demo.common.enums.ResultEnum;
import com.zkxh.demo.common.handle.RuntimeServiceException;
import com.zkxh.demo.dao.staff.StaffDeptMapper;
import com.zkxh.demo.model.staff.StaffDept;
import com.zkxh.demo.model.staff.StaffDeptExample;
import com.zkxh.demo.service.staff.StaffService;
import com.zkxh.demo.service.staff.dept.StaffDeptService;
import com.zkxh.demo.vo.req.StaffDeptVoReq;
import com.zkxh.demo.vo.resp.StaffDeptVoResp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName StaffDeptServiceImpl
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/25 13:41
 * @Vserion v0.0.1
 */
@Service
public class StaffDeptServiceImpl implements StaffDeptService {


    @Resource
    private StaffDeptMapper staffDeptMapper;

    @Override
    public Page getAllStaffDept(Integer startPage, Integer pageSize) {

        Page page = PageHelper.startPage(startPage, pageSize);

        List<StaffDept> list = staffDeptMapper.selectStaffDepts();

        List<StaffDeptVoResp> staffDeptVoRespList = Collections.synchronizedList(new ArrayList<>());

        StaffDeptVoResp staffDeptVoResp = null;

        for (StaffDept staffDept : list) {
            staffDeptVoResp = new StaffDeptVoResp();
            staffDeptVoResp.setDeptId(staffDept.getDeptId());
            staffDeptVoResp.setDeptName(staffDept.getDeptName());
            staffDeptVoResp.setStaffList(staffDept.getStaffList());
            staffDeptVoResp.setStaffGroups(staffDept.getStaffGroupList());
            staffDeptVoRespList.add(staffDeptVoResp);
        }
        return page;
    }

    @Override
    public Integer addStaffDept(StaffDeptVoReq staffDeptVoReq) {

        StaffDept staffDept = new StaffDept();

        staffDept.setDeptId(staffDeptVoReq.getDeptId());
        staffDept.setDeptName(staffDeptVoReq.getDeptName());
        return staffDeptMapper.insert(staffDept);
    }

    @Override
    public Integer updateStaffDept(StaffDeptVoReq staffDeptVoReq) {
        StaffDept staffDept = new StaffDept();
        staffDept.setDeptId(staffDeptVoReq.getDeptId());
        staffDept.setDeptName(staffDeptVoReq.getDeptName());
        return staffDeptMapper.updateByPrimaryKey(staffDept);
    }

    @Override
    public Integer deleteStaffDeptById(Integer[] ids) {
        int len = 0;

        for (Integer id : ids) {
            Integer res = staffDeptMapper.countGroupNumByDeptId(id);
            if (res > 0)
                throw new RuntimeServiceException(ResultEnum.DATA_IS_NOT_NULL);
            staffDeptMapper.deleteByPrimaryKey(id);
            len++;
        }
        if (ids.length == len) {
            return len;
        } else {
            return 0;
        }
    }

    @Override
    public Page getAllStaffDeptByParams(Integer startPage, Integer pageSize, StaffDeptVoReq staffDeptVoReq) {

        StaffDept staffDept = new StaffDept();
        staffDept.setDeptName(staffDeptVoReq.getDeptName());
        staffDept.setDeptId(staffDeptVoReq.getDeptId());

        Page page = PageHelper.startPage(startPage, pageSize);
        List<StaffDept> list = staffDeptMapper.selectStaffDeptsByParams(staffDept);

        List<StaffDeptVoResp> staffDeptVoRespList = Collections.synchronizedList(new ArrayList<>());

        StaffDeptVoResp staffDeptVoResp = null;

        for (StaffDept dept : list) {
            staffDeptVoResp = new StaffDeptVoResp();
            staffDeptVoResp.setDeptId(dept.getDeptId());
            staffDeptVoResp.setDeptName(dept.getDeptName());
            staffDeptVoResp.setStaffList(dept.getStaffList());
            staffDeptVoResp.setStaffGroups(dept.getStaffGroupList());
            staffDeptVoRespList.add(staffDeptVoResp);
        }
        System.out.println(staffDeptVoRespList.toString());
        return page;
    }

    @Override
    public boolean checkDeptIsNotIncludeGroup(Integer deptId) {
        Integer res = staffDeptMapper.countGroupNumByDeptId(deptId);
        if (res > 0) {
            return true;
        }
        return false;
    }
}
