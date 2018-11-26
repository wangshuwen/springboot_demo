package com.zkxh.demo.service.zone;

import com.zkxh.demo.dto.zone.ZoneDto;
import com.zkxh.demo.model.zone.Zone;

import java.util.List;

/**
 * @ClassName ZoneService
 * @Description
 * @Auther lifeng
 * @DATE 2018/11/22 10:42
 * @Vserion v0.0.1
 */

public interface ZoneService {

    void findAllLargeAreas();

    int addZone(List<Zone> zone);

    int updateZone(Zone zone);

    int deleteZone(Integer[] ids);

    void findAllZoneByParam();
}
