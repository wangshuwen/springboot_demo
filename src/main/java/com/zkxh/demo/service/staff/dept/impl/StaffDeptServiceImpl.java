package com.zkxh.demo.service.staff.dept.impl;

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
    public List<StaffDeptVoResp> getAllDept() {

        List<StaffDept> list = staffDeptMapper.selectStaffDepts();

        List<StaffDeptVoResp> staffDeptVoRespList = Collections.synchronizedList(new ArrayList<>());

        StaffDeptVoResp staffDeptVoResp = null;

        for (StaffDept staffDept : list) {
            staffDeptVoResp = new StaffDeptVoResp();
            staffDeptVoResp.setDeptId(staffDept.getDeptId());
            staffDeptVoResp.setDeptName(staffDept.getDeptName());
            staffDeptVoRespList.add(staffDeptVoResp);
        }
        return staffDeptVoRespList;
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
    public Integer deleteStaffDeptById(List<Integer> ids) {
        int len = 0;

        for (Integer id : ids) {
            staffDeptMapper.deleteByPrimaryKey(id);
            len++;
        }
        if (ids.size() == len) {
            return len;
        } else {
            return 0;
        }
    }
}
