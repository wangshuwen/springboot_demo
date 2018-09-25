package com.zkxh.demo.service.staff.group.impl;

import com.zkxh.demo.dao.staff.StaffGroupMapper;
import com.zkxh.demo.model.staff.StaffGroup;
import com.zkxh.demo.model.staff.StaffGroupExample;
import com.zkxh.demo.service.staff.group.StaffGroupService;
import com.zkxh.demo.vo.req.StaffGroupReqVo;
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

        return staffGroupMapper.updateByPrimaryKey(staffGroup);
    }

    @Override
    public Integer deleteStaffGroupsByGroupId(List<Integer> ids) {

        int count = 0;

        for (Integer id : ids) {
            staffGroupMapper.deleteByPrimaryKey(id);
            count++;

        }
        if (count == ids.size()) {
            return count;
        } else {
            return 0;
        }
    }

    @Override
    public List<StaffGroupRespVo> getStaffGroupByDeptId(Integer staffDeptId) {

        StaffGroup staffGroup = new StaffGroup();
        staffGroup.setDeptId(staffDeptId);

        StaffGroupExample staffGroupExample = new StaffGroupExample();
        List<Integer> listOfstaffDeptId = new ArrayList();
        staffGroupExample.createCriteria().andDeptIdIn(listOfstaffDeptId);

        List<StaffGroup> staffGroupSList = staffGroupMapper.selectByExample(staffGroupExample);

        StaffGroupRespVo staffGroupRespVo = null;

        List<StaffGroupRespVo> list = Collections.synchronizedList(new ArrayList<>());


        for (StaffGroup group : staffGroupSList) {
            staffGroupRespVo = new StaffGroupRespVo();

            staffGroupRespVo.setDeptId(group.getDeptId());
            staffGroupRespVo.setGroupId(group.getGroupId());
            staffGroupRespVo.setGroupName(group.getGroupName());

            list.add(staffGroupRespVo);
        }

        return list;
    }
}
