package com.zkxh.demo.service.base_station;

import com.github.pagehelper.PageInfo;
import com.zkxh.demo.model.base_station.BaseStation;

import java.util.Date;

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
}
