package com.zkxh.demo.dao.staff;

import com.zkxh.demo.dto.staff.StaffGroupDto;
import com.zkxh.demo.model.staff.StaffGroup;
import com.zkxh.demo.model.staff.StaffGroupExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StaffGroupMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_group
     *
     * @mbg.generated
     */
    long countByExample(StaffGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_group
     *
     * @mbg.generated
     */
    int deleteByExample(StaffGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_group
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer groupId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_group
     *
     * @mbg.generated
     */
    int insert(StaffGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_group
     *
     * @mbg.generated
     */
    int insertSelective(StaffGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_group
     *
     * @mbg.generated
     */
    List<StaffGroup> selectByExample(StaffGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_group
     *
     * @mbg.generated
     */
    StaffGroup selectByPrimaryKey(Integer groupId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_group
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") StaffGroup record, @Param("example") StaffGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_group
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") StaffGroup record, @Param("example") StaffGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_group
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StaffGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_group
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StaffGroup record);

    List<StaffGroup> selectAllStaffGroups();

    List<StaffGroup> selectStaffGroupsByDeptId(Integer deptId);

    List<StaffGroupDto> selectStaffGroupsByDeptIdOfStaffDept(Integer deptId);


    Integer countStaffNumByGroupId(Integer groupId);

    StaffGroupDto selectGroupDtoByGroupId(Integer groupId);
}