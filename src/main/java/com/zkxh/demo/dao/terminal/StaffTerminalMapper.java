package com.zkxh.demo.dao.terminal;

import com.zkxh.demo.model.terminal.StaffTerminal;
import com.zkxh.demo.model.terminal.StaffTerminalExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface StaffTerminalMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_terminal
     *
     * @mbg.generated
     */
    long countByExample(StaffTerminalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_terminal
     *
     * @mbg.generated
     */
    int deleteByExample(StaffTerminalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_terminal
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer staffTerminalId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_terminal
     *
     * @mbg.generated
     */
    int insert(StaffTerminal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_terminal
     *
     * @mbg.generated
     */
    int insertSelective(StaffTerminal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_terminal
     *
     * @mbg.generated
     */
    List<StaffTerminal> selectByExample(StaffTerminalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_terminal
     *
     * @mbg.generated
     */
    StaffTerminal selectByPrimaryKey(Integer staffTerminalId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_terminal
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") StaffTerminal record, @Param("example") StaffTerminalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_terminal
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") StaffTerminal record, @Param("example") StaffTerminalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_terminal
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StaffTerminal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table staff_terminal
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StaffTerminal record);


    List<Map<String, Object>> selectStaffNameByTerminalId(Integer terminalId);

    List<StaffTerminal> selectAllTerminals();

    List<StaffTerminal> getNotBinDingTerminals();

    Map<String, Object> selectStaffInfoMapByTerminal(Integer terminalId);

    Integer selectTerminalIdByStaffId(Integer staffId);

    //绑定
    Integer updateTerminalBinding(@Param("staffId") Integer staffId, @Param("terminalId") Integer terminalId);

    //解绑
    Integer updateTerminalUnBinding(@Param("terminalId") Integer terminalId);

    //根据员工ID 解绑
    Integer updateTerminalUnBindingByStaffId(@Param("staffId") Integer staffId);

    List<StaffTerminal> selectTerminalsByParams(StaffTerminal staffTerminal);

    int deleteByTerminalId(@Param("terminalId") Integer item);

    //通过terminalId更新终端信息
    int updateByTerminalId(StaffTerminal staffTerminal);

    boolean countTerminalNumByTerminalId(Integer terminalId);

    boolean countTerminalNumByStaffId(Integer staffId);
}
