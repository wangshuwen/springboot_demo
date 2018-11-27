package com.zkxh.demo.controller.zone;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkxh.demo.common.enums.ResultEnum;
import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.model.area.Area;
import com.zkxh.demo.model.base_station.BaseStation;
import com.zkxh.demo.model.zone.Zone;
import com.zkxh.demo.service.area.AreaService;
import com.zkxh.demo.service.base_station.BaseStationService;
import com.zkxh.demo.service.zone.ZoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName ZoneController
 * @Description
 * @Auther lifeng
 * @DATE 2018/11/22 10:39
 * @Vserion v0.0.1
 */
@RestController
@Api(value = "ZoneController", tags = "矿下大区域操作")
@RequestMapping("zone/")
public class ZoneController {

   @Resource
    private ZoneService zoneService;

    @Resource
    private AreaService areaService;

    @Resource
    private BaseStationService baseStationService;


    @GetMapping("getZoneInfo")
    @ApiOperation(value = "获取大区消息", notes = "大区信息,以及下面的区域数量,下面的基站数量")
    public String getZoneInfo(@RequestParam(name = "zoneId") Integer zoneId) {
        HashMap<String, Object> map = new HashMap<>();

        Zone zone = zoneService.selectByPrimaryKey(zoneId);
        if(zone!=null){
            List<Area> areaList = areaService.findAllAreaByParentId(zone.getZoneId());
            List<BaseStation> stationList=  baseStationService.findAllStationByZoneId(zone.getZoneId());
            map.put("zoneId",zone.getZoneId());
            map.put("zoneName",zone.getZoneName());
            map.put("areaCount",areaList==null?0:areaList.size());
            map.put("stationCount",stationList==null?0:stationList.size());
        }

        return ResultUtil.jsonToStringSuccess(map);
    }





    @GetMapping("getZonesInfo")
    @ApiOperation(value = "获取所有矿下大区", notes = "大区信息,以及下面的区域数量,下面的基站数量，做到分页查询")
    public String getZonesInfo(@RequestParam(name = "page", defaultValue = "1", required = false) Integer startPage,
                          @RequestParam(name = "limit", defaultValue = "10", required = false) Integer pageSize,
                          @RequestParam(name = "zoneName",defaultValue = "",required = false) String zoneName) {
        Page page = PageHelper.startPage(startPage, pageSize);
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        List<Zone> zoneList = zoneService.findAllZoneInfo(zoneName);
       if(zoneList != null && zoneList.size() > 0 ){
           for (Zone zone : zoneList) {
               HashMap<String, Object> map = new HashMap<>();
               List<Area> areaList = areaService.findAllAreaByParentId(zone.getZoneId());
               List<BaseStation> stationList=  baseStationService.findAllStationByZoneId(zone.getZoneId());
               map.put("zoneId",zone.getZoneId());
               map.put("zoneName",zone.getZoneName());
               map.put("areaCount",areaList==null?0:areaList.size());
               map.put("stationCount",stationList==null?0:stationList.size());
               list.add(map);
           }
       }
        PageInfo info = new PageInfo<>(list);

        return ResultUtil.jsonToStringSuccess(info);
    }


    @PostMapping("addZone")
    @ApiOperation(value = "添加所有区域信息")
    public String addZone(@RequestBody List<Zone> zones) {
        int result = zoneService.addZones(zones);
        return result >0 ? ResultUtil.jsonToStringSuccess(zones) : ResultUtil.jsonToStringError(ResultEnum.ADD_ZONE_ERROR);
    }

    @PutMapping("updateZone")
    @ApiOperation(value = "修改区域信息", notes = "修改区域信息")
    public String modifyZone(@RequestBody Zone zone) {
        int result = zoneService.updateZone(zone);
        return result == 1 ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.UPDATE_ZONE_ERROR);
    }

    @Transactional
    @DeleteMapping("deleteZone")
    @ApiOperation(value = "删除大区信息", notes = "可以批量删除，若大区下存在子分区则不许删除")
    public String deleteZone(@RequestParam(name = "ids", required = true) Integer[] ids) {
        int result = 0;
        if (ids != null && ids.length > 0)
            result = zoneService.deleteZone(ids);
        return result == ids.length ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.DELETE_ZONE_ERROR);
    }

}
