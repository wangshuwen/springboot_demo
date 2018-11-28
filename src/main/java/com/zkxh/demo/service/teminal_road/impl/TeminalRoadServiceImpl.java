package com.zkxh.demo.service.teminal_road.impl;

import com.zkxh.demo.dao.teminal_road.TeminalRoadMapper;
import com.zkxh.demo.model.teminal_road.TeminalRoad;
import com.zkxh.demo.model.teminal_road.TeminalRoadExample;
import com.zkxh.demo.service.teminal_road.TeminalRoadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangshuwen
 * @Description:
 * @Date 2018/11/28/16:59
 */
@Service
public class TeminalRoadServiceImpl implements TeminalRoadService{
    @Resource
    private TeminalRoadMapper teminalRoadMapper;

    @Override
    public int deleteByPrimaryKey(Integer teminalRoadId) {
        return teminalRoadMapper.deleteByPrimaryKey(teminalRoadId);
    }

    @Override
    public int insert(TeminalRoad record) {
        return teminalRoadMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKey(TeminalRoad record) {
        return teminalRoadMapper.updateByPrimaryKey(record);
    }

    @Override
    public TeminalRoad selectByPrimaryKey(Integer teminalRoadId) {
        return teminalRoadMapper.selectByPrimaryKey(teminalRoadId);
    }

    @Override
    public List<TeminalRoad> selectByExample(TeminalRoadExample example) {
        return teminalRoadMapper.selectByExample(example);
    }

    @Override
    public List<TeminalRoad> findListByTeminalId(int teminalId) {
        TeminalRoadExample example = new TeminalRoadExample();
        TeminalRoadExample.Criteria criteria = example.createCriteria();
        criteria.andTeminalRoadIdEqualTo(teminalId);
        return teminalRoadMapper.selectByExample(example);
    }
}
