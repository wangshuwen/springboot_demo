package com.zkxh.demo.service.base_station.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkxh.demo.dao.base_station.BaseStationMapper;
import com.zkxh.demo.model.base_station.BaseStation;
import com.zkxh.demo.model.base_station.BaseStationExample;
import com.zkxh.demo.service.base_station.BaseStationService;
import com.zkxh.demo.vo.resp.BaseStationPositionVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName BaseStationServiceImpl
 * @Description
 * @Auther lifeng
 * @DATE 2018/10/26 16:07
 * @Vserion v0.0.1
 */
@Service
public class BaseStationServiceImpl implements BaseStationService {

    @Resource
    private BaseStationMapper baseStationMapper;

    @Override
    public int addBaseStation(BaseStation station) {
        return baseStationMapper.insertSelective(station);
    }

    @Override
    public PageInfo<BaseStation> findBaseStationInfo(String begin, String end, Integer startPage, Integer pageSize) {
        PageHelper.startPage(startPage, pageSize);
        List<BaseStation> list = baseStationMapper.selectBaseStationInfoByTime(begin, end);
        PageInfo<BaseStation> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<BaseStation> findAllBaseStationInfo(Integer startPage, Integer pageSize) {
        PageHelper.startPage(startPage, pageSize);
        List<BaseStation> list = baseStationMapper.selectAllBaseStationInfo();
        PageInfo<BaseStation> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<BaseStation> findBaseStationInfoByStart(String begin, Integer startPage, Integer pageSize) {
        PageHelper.startPage(startPage, pageSize);
        List<BaseStation> list = baseStationMapper.selectBaseStationInfoByStartTime(begin);
        PageInfo<BaseStation> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<BaseStation> findBaseStationInfoByEnd(String end, Integer startPage, Integer pageSize) {
        PageHelper.startPage(startPage, pageSize);
        List<BaseStation> list = baseStationMapper.selectBaseStationInfoByEndTime(end);
        PageInfo<BaseStation> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int updateStationInfo(BaseStation station) {
        return baseStationMapper.updateByPrimaryKeySelective(station);
    }

    @Override
    public int deleteStationById(Integer id) {
        return baseStationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteStationByIds(Integer[] ids) {
        int count = 0;
        for (Integer item : ids) {
            if (baseStationMapper.deleteByPrimaryKey(item) == 1)
                count++;
        }
        return count;
    }

    @Override
    public List<BaseStationPositionVO> findBaseStationPositionInfo() {
        List<Map<String, Object>> maps = baseStationMapper.selectBaseStationPositionInfo();

        BaseStationPositionVO baseStationPositionVO = null;
        List<BaseStationPositionVO> list = Collections.synchronizedList(new ArrayList<>());
        for (Map<String, Object> item : maps) {
            baseStationPositionVO = new BaseStationPositionVO();

            baseStationPositionVO.setBaseStationId((Integer) item.get("base_station_id"));
            baseStationPositionVO.setBaseStationNum((Integer) item.get("base_station_num"));
            baseStationPositionVO.setPositionX((double) item.get("position_x"));
            baseStationPositionVO.setPositionY((double) item.get("position_y"));
            baseStationPositionVO.setPositionZ((double) item.get("position_z"));

            list.add(baseStationPositionVO);
        }
        return list;
    }


    @Override
    public List<BaseStationPositionVO> findBaseStationPositionInfoNotUsed() {

        List<Map<String, Object>> maps = baseStationMapper.selectBaseStationPositionInfoNotUsed();

        BaseStationPositionVO baseStationPositionVO = null;
        List<BaseStationPositionVO> list = Collections.synchronizedList(new ArrayList<>());
        for (Map<String, Object> item : maps) {
            baseStationPositionVO = new BaseStationPositionVO();

            baseStationPositionVO.setBaseStationId((Integer) item.get("base_station_id"));
            baseStationPositionVO.setBaseStationNum((Integer) item.get("base_station_num"));
//            baseStationPositionVO.setPositionX((double) item.get("position_x"));
//            baseStationPositionVO.setPositionY((double) item.get("position_y"));
//            baseStationPositionVO.setPositionZ((double) item.get("position_z"));

            list.add(baseStationPositionVO);
        }
        return list;
    }

    @Override
    public boolean checkStationExists(Integer baseStationNum) {
        return baseStationMapper.selectCountStationByBaseStationNum(baseStationNum);
    }

    @Override
    public List<BaseStation> findAllStationByZoneId(Integer zoneId) {
        BaseStationExample stationExample = new BaseStationExample();
        BaseStationExample.Criteria criteria = stationExample.createCriteria();
        criteria.andZoneIdEqualTo(zoneId);
        return baseStationMapper.selectByExample(stationExample);
    }

    @Override
    public List<BaseStation> findAllStationByAreaId(Integer areaId) {

        return baseStationMapper.findAllStationByAreaId(areaId);
    }

    @Override
    public BaseStation findBaseStationById(int stationId) {
        return baseStationMapper.findBaseStationById(stationId);
    }


}
