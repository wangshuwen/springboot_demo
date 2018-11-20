package com.zkxh.demo.dao.terminal;

import com.zkxh.demo.model.terminal.TerminalUpdateIp;
import com.zkxh.demo.model.terminal.TerminalUpdateIpExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TerminalUpdateIpMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_update_ip
     *
     * @mbg.generated
     */
    long countByExample(TerminalUpdateIpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_update_ip
     *
     * @mbg.generated
     */
    int deleteByExample(TerminalUpdateIpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_update_ip
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_update_ip
     *
     * @mbg.generated
     */
    int insert(TerminalUpdateIp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_update_ip
     *
     * @mbg.generated
     */
    int insertSelective(TerminalUpdateIp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_update_ip
     *
     * @mbg.generated
     */
    List<TerminalUpdateIp> selectByExample(TerminalUpdateIpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_update_ip
     *
     * @mbg.generated
     */
    TerminalUpdateIp selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_update_ip
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TerminalUpdateIp record, @Param("example") TerminalUpdateIpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_update_ip
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TerminalUpdateIp record, @Param("example") TerminalUpdateIpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_update_ip
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TerminalUpdateIp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_update_ip
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TerminalUpdateIp record);

    void updateIpInfoByTerminalId(TerminalUpdateIp terminalUpdateIp);

    boolean checkTerminalIdIsNotExist(Integer terminalId);

    Map<String, Object> selectTerminalIpInfoByTerminalId(Integer terminalId);
}
