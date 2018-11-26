package com.zkxh.demo.dao.base_station;

import com.zkxh.demo.model.base_station.BaseStation;
import com.zkxh.demo.model.base_station.BaseStationExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BaseStationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_station
     *
     * @mbg.generated
     */
    long countByExample(BaseStationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_station
     *
     * @mbg.generated
     */
    int deleteByExample(BaseStationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_station
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer baseStationId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_station
     *
     * @mbg.generated
     */
    int insert(BaseStation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_station
     *
     * @mbg.generated
     */
    int insertSelective(BaseStation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_station
     *
     * @mbg.generated
     */
    List<BaseStation> selectByExample(BaseStationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_station
     *
     * @mbg.generated
     */
    BaseStation selectByPrimaryKey(Integer baseStationId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_station
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") BaseStation record, @Param("example") BaseStationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_station
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") BaseStation record, @Param("example") BaseStationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_station
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(BaseStation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_station
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(BaseStation record);


    List<BaseStation> selectBaseStationInfoByTime(@Param("begin") String begin, @Param("end") String end);

    List<BaseStation> selectAllBaseStationInfo();

    List<BaseStation> selectBaseStationInfoByStartTime(String begin);

    List<BaseStation> selectBaseStationInfoByEndTime(String end);

    List<Map<String, Object>> selectBaseStationPositionInfo();

    List<Map<String, Object>> selectBaseStationPositionInfoNotUsed();

    boolean selectCountStationByBaseStationNum(Integer baseStationNum);
}