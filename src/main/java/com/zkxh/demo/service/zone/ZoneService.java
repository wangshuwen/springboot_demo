package com.zkxh.demo.service.zone;

import com.zkxh.demo.dto.zone.ZoneDto;
import com.zkxh.demo.model.zone.Zone;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ZoneService
 * @Description
 * @Auther lifeng
 * @DATE 2018/11/22 10:42
 * @Vserion v0.0.1
 */

public interface ZoneService {
    int deleteByPrimaryKey(Integer zoneId);

    int insert(Zone record);

    Zone selectByPrimaryKey(Integer zoneId);

    int updateByPrimaryKey(Zone record);

    List<Map<String, Object>> selectZoneByParentId(int parentId);

    int addZones(List<Zone> zone);

    int updateZone(Zone zone);

    int deleteZone(Integer[] ids);

    List<Zone> findAllZoneInfo(String zoneName);

    public void findAllLargeAreas();


}
