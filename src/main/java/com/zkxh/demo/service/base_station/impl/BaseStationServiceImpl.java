package com.zkxh.demo.service.base_station.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkxh.demo.dao.base_station.BaseStationMapper;
import com.zkxh.demo.model.base_station.BaseStation;
import com.zkxh.demo.service.base_station.BaseStationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
            baseStationMapper.deleteByPrimaryKey(item);
            count++;
        }
        return count;
    }
}
