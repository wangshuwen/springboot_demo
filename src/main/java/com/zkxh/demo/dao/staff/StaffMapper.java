package com.zkxh.demo.dao.staff;

import com.zkxh.demo.dto.staff.StaffDto;
import com.zkxh.demo.model.staff.Staff;
import com.zkxh.demo.model.staff.StaffExample;
import java.util.List;
import java.util.Map;

import com.zkxh.demo.dto.staff.StaffInfoDto;
import org.apache.ibatis.annotations.Param;

public interface StaffMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff
     *
     * @mbg.generated
     */
    long countByExample(StaffExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff
     *
     * @mbg.generated
     */
    int deleteByExample(StaffExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer staffId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff
     *
     * @mbg.generated
     */
    int insert(Staff record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff
     *
     * @mbg.generated
     */
    int insertSelective(Staff record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff
     *
     * @mbg.generated
     */
    List<Staff> selectByExample(StaffExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff
     *
     * @mbg.generated
     */
    Staff selectByPrimaryKey(Integer staffId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Staff record, @Param("example") StaffExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Staff record, @Param("example") StaffExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Staff record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Staff record);

    List<StaffDto> selectStaffsByGroupId(Integer groupId);

    List<StaffInfoDto> selectStaffByParams(Staff staffVo);

    Map<String, Object> selectGroupAndDeptByStaffId(Integer staffId);

    Map<String, Object> selectStaffInfoByTerminalId(Integer terminalId);
}
