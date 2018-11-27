package com.zkxh.demo.service.area;

import com.zkxh.demo.model.area.Area;
import com.zkxh.demo.model.zone.Zone;

import java.util.List;
import java.util.Map;

/**
 * @author wangshuwen
 * @Description:
 * @Date 2018/11/27/9:13
 */
public interface AreaService {
    List<Area> findAllAreaByParentId(int parentId);

    int deleteByPrimaryKey(Integer areaId);

    int insert(Area area);

    Area selectByPrimaryKey(Integer areaId);



    int addAreas(List<Area> areaList);

    int updateArea(Area area);

    int deleteAreas(Integer[] ids);



}
