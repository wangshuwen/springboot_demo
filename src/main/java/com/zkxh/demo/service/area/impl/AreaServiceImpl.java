package com.zkxh.demo.service.area.impl;

import com.zkxh.demo.dao.area.AreaMapper;
import com.zkxh.demo.model.area.Area;
import com.zkxh.demo.model.area.AreaExample;

import com.zkxh.demo.service.area.AreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangshuwen
 * @Description:
 * @Date 2018/11/27/9:14
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Resource
    private AreaMapper areaMapper;

    @Override
    public List<Area> findAllAreaByParentId(int parentId) {
        AreaExample areaExample = new AreaExample();
        AreaExample.Criteria criteria = areaExample.createCriteria();
        criteria.andAreaParentIdEqualTo(parentId);
        List<Area> areas = areaMapper.selectByExample(areaExample);
        return areas;
    }

    @Override
    public int deleteByPrimaryKey(Integer areaId) {
        return areaMapper.deleteByPrimaryKey(areaId);
    }

    @Override
    public int insert(Area area) {
        return areaMapper.insert(area);
    }

    @Override
    public Area selectByPrimaryKey(Integer areaId) {
        return areaMapper.selectByPrimaryKey(areaId);
    }

    @Override
    public int addAreas(List<Area> areaList) {
        int count=0;
        for (Area area : areaList) {
            count+=areaMapper.insert(area);
        }
        return count;
    }

    @Override
    public int updateArea(Area area) {
        return areaMapper.updateByPrimaryKey(area);
    }

    @Override
    public int deleteAreas(Integer[] ids) {
        int count=0;
        for (Integer id : ids) {
            count+=areaMapper.deleteByPrimaryKey(id);
        }
        return count;
    }
}
