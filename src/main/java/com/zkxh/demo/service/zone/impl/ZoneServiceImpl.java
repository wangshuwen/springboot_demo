package com.zkxh.demo.service.zone.impl;

import com.zkxh.demo.dao.zone.ZoneMapper;
import com.zkxh.demo.dto.zone.ZoneDto;
import com.zkxh.demo.model.zone.Zone;
import com.zkxh.demo.service.zone.ZoneService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ZoneServiceImpl
 * @Description
 * @Auther lifeng
 * @DATE 2018/11/22 10:43
 * @Vserion v0.0.1
 */
@Service
public class ZoneServiceImpl implements ZoneService {

    @Resource
    private ZoneMapper zoneMapper;

    @Override
    public void findAllLargeAreas() {

        zoneMapper.selectZoneByParentId(0);
//        List<Map<String,Object>> listOfMap = zoneMapper.selectZoneByParentId(0);
//
//        List<ZoneDto> list = Collections.synchronizedList(new ArrayList<>());
//
//        Zone zone = null;
//
//        ZoneDto zoneDto = null;
//
//        for (Map<String,Object> item: listOfMap){
//            zone = new Zone();
//            zoneDto = new ZoneDto();
//
//            zone.setZoneId((Integer) item.get("zone_id"));
//            zone.setZoneName((String)item.get("zone_name"));
//            zone.setZoneParentId((Integer) item.get("zone_parent_id"));
////            zone.setZoneSeq((String)item.get("zone_seq"));
//            zone.setZoneType((Integer) item.get("zone_type"));
//            zoneDto.setZone(zone);
//            list.add(zoneDto);
//        }

//        return list;
    }

    @Override
    public int addZone(List<Zone> zone) {
        return 0;
    }

    @Override
    public int updateZone(Zone zone) {
        return 0;
    }

    @Override
    public int deleteZone(Integer[] ids) {
        return 0;
    }

    @Override
    public void findAllZoneByParam() {

    }
}
