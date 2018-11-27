package com.zkxh.demo.service.base_station;

import com.github.pagehelper.PageInfo;
import com.zkxh.demo.model.base_station.BaseStation;
import com.zkxh.demo.vo.resp.BaseStationPositionVO;

import java.util.List;

/**
 * @ClassName BaseStationService
 * @Description
 * @Auther lifeng
 * @DATE 2018/10/26 16:06
 * @Vserion v0.0.1
 */

public interface BaseStationService {

    int addBaseStation(BaseStation station);

    PageInfo<BaseStation> findBaseStationInfo(String begin, String end, Integer startPage, Integer pageSize);

    PageInfo<BaseStation> findAllBaseStationInfo(Integer startPage, Integer pageSize);

    PageInfo<BaseStation> findBaseStationInfoByStart(String begin, Integer startPage, Integer pageSize);

    PageInfo<BaseStation> findBaseStationInfoByEnd(String end, Integer startPage, Integer pageSize);

    int updateStationInfo(BaseStation station);

    int deleteStationById(Integer id);

    int deleteStationByIds(Integer[] ids);

    List<BaseStationPositionVO> findBaseStationPositionInfo();

    List<BaseStationPositionVO> findBaseStationPositionInfoNotUsed();

    /**
     * @param
     * @return boolean
     * @description 判断 基站的ID 是否存在  ，存在返回true   不存在返回false
     * @date 10:31 2018/11/20
     * @auther lifeng
     **/
    boolean checkStationExists(Integer baseStationNum);

    List<BaseStation> findAllStationByZoneId(Integer zoneId);
}
