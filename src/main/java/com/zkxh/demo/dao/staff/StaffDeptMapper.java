package com.zkxh.demo.dao.staff;

import com.zkxh.demo.model.staff.StaffDept;
import com.zkxh.demo.model.staff.StaffDeptExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StaffDeptMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_dept
     *
     * @mbg.generated
     */
    long countByExample(StaffDeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_dept
     *
     * @mbg.generated
     */
    int deleteByExample(StaffDeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_dept
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer deptId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_dept
     *
     * @mbg.generated
     */
    int insert(StaffDept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_dept
     *
     * @mbg.generated
     */
    int insertSelective(StaffDept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_dept
     *
     * @mbg.generated
     */
    List<StaffDept> selectByExample(StaffDeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_dept
     *
     * @mbg.generated
     */
    StaffDept selectByPrimaryKey(Integer deptId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_dept
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") StaffDept record, @Param("example") StaffDeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_dept
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") StaffDept record, @Param("example") StaffDeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_dept
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StaffDept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_dept
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StaffDept record);

    List<StaffDept> selectStaffDepts();

    List<StaffDept> selectStaffDeptsByParams(StaffDept staffDept);

    Integer countGroupNumByDeptId(Integer deptId);
}