package com.zkxh.demo.dao.rt_gas;

import com.zkxh.demo.dto.gas.GasInfoAndStaffDto;
import com.zkxh.demo.model.rt_gas.RtGasInfo;
import com.zkxh.demo.model.rt_gas.RtGasInfoExample;
import java.util.List;
import java.util.Map;

import com.zkxh.demo.vo.resp.GasWSRespVO;
import org.apache.ibatis.annotations.Param;

public interface RtGasInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rt_gas_info
     *
     * @mbg.generated
     */
    long countByExample(RtGasInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rt_gas_info
     *
     * @mbg.generated
     */
    int deleteByExample(RtGasInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rt_gas_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer rtGasInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rt_gas_info
     *
     * @mbg.generated
     */
    int insert(RtGasInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rt_gas_info
     *
     * @mbg.generated
     */
    int insertSelective(RtGasInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rt_gas_info
     *
     * @mbg.generated
     */
    List<RtGasInfo> selectByExample(RtGasInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rt_gas_info
     *
     * @mbg.generated
     */
    RtGasInfo selectByPrimaryKey(Integer rtGasInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rt_gas_info
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") RtGasInfo record, @Param("example") RtGasInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rt_gas_info
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") RtGasInfo record, @Param("example") RtGasInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rt_gas_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RtGasInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rt_gas_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RtGasInfo record);

    List<GasWSRespVO> selectGasInfoByStaffName(String staffName);

    Map<String, Object> selectGasInfoByTerminalLastTime(Integer terminalId);

    List<Map<String, Object>> selectGasInfoLastTenRecords(int number);
}
