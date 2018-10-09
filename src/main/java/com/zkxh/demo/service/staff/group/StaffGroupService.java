package com.zkxh.demo.service.staff.group;

import com.zkxh.demo.vo.req.StaffGroupReqVo;
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

    List<StaffGroupRespVo> getStaffGroupByDeptId(Integer staffDeptId);

    List<StaffGroupRespVo> getAllStaffGroupInfo();

    boolean checkGroupIsNotIncludeStaff(Integer deptId);
}
