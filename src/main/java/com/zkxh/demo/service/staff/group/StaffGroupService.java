package com.zkxh.demo.service.staff.group;

import com.github.pagehelper.Page;
import com.zkxh.demo.vo.req.StaffGroupReqVo;
import com.zkxh.demo.vo.req.StaffInfoVO;
import com.zkxh.demo.vo.resp.StaffGroupRespVo;

import java.util.List;

/**
 * @ClassName StaffGroupService
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/25 14:19
 * @Vserion v0.0.1
 */

public interface StaffGroupService {

    Integer addStaffGroup(StaffGroupReqVo staffGroupReqVo);

    Integer updateStaffGroup(StaffGroupReqVo staffGroupReqVo);

    Integer deleteStaffGroupsByGroupId(Integer[] ids);

    Page getStaffGroupByDeptId(Integer startPage, Integer pageSize, Integer staffDeptId);

    Page getAllStaffGroupInfo(Integer startPage, Integer pageSize);

    boolean checkGroupIsNotIncludeStaff(Integer deptId);

    Page getStaffInfoByGroupId(Integer groupId, Integer startPage, Integer pageSize);
}
