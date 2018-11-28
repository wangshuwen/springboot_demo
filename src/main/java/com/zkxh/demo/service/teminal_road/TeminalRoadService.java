package com.zkxh.demo.service.teminal_road;

import com.zkxh.demo.model.teminal_road.TeminalRoad;
import com.zkxh.demo.model.teminal_road.TeminalRoadExample;

import java.util.List;

/**
 * @author wangshuwen
 * @Description:
 * @Date 2018/11/28/16:58
 */
public interface TeminalRoadService {
    int deleteByPrimaryKey(Integer teminalRoadId);
    int insert(TeminalRoad record);
    int updateByPrimaryKey(TeminalRoad record);

    TeminalRoad selectByPrimaryKey(Integer teminalRoadId);

    List<TeminalRoad> selectByExample(TeminalRoadExample example);


    List<TeminalRoad> findListByTeminalId(int teminalId);
}
