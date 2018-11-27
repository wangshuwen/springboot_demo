package com.zkxh.demo.controller.area;

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
 * @author wangshuwen
 * @Description:
 * @Date 2018/11/27/9:12
 */
@RestController
@Api(value = "AreaController", tags = "空间下区域操作")
@RequestMapping("area/")
public class AreaController {
    @Resource
    private ZoneService zoneService;

    @Resource
    private AreaService areaService;

    @Resource
    private BaseStationService baseStationService;


    @GetMapping("getAreasInfo")
    @ApiOperation(value = "获取大区下的所有区域", notes = "所属空间  区域拥有该有的基站数量")
    public String getAreasInfo(@RequestParam(name = "page", defaultValue = "1", required = false) Integer startPage,
                          @RequestParam(name = "limit", defaultValue = "10", required = false) Integer pageSize,
                          @RequestParam(name = "zoneId",defaultValue = "0",required = false) Integer zoneId) {
        PageHelper.startPage(startPage, pageSize);
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        System.out.println(zoneId);
            List<Area> areaList = areaService.findAllAreaByParentId(zoneId);
        if(areaList!=null&&areaList.size()>0){
            for (Area area : areaList) {
                List<BaseStation> stationList= baseStationService.findAllStationByAreaId(area.getAreaId());
                HashMap<String, Object> map = new HashMap<>();
                map.put("areaId",area.getAreaId());
                map.put("areaName",area.getAreaName());
                map.put("zoneId",area.getAreaParentId());
                map.put("stationCount",stationList.size());
                list.add(map);
            }
        }

        PageInfo info = new PageInfo<>(list);
        return ResultUtil.jsonToStringSuccess(info);
    }


    @PostMapping("addArea")
    @ApiOperation(value = "添加多个区域信息")
    public String addArea(@RequestBody List<Area> areaList) {
        int result = areaService.addAreas(areaList);
        return result ==areaList.size() ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.ADD_ZONE_ERROR);
    }

    @PutMapping("updateArea")
    @ApiOperation(value = "修改区域信息", notes = "修改区域信息")
    public String modifyZone(@RequestBody Area area) {
        int result = areaService.updateArea(area);
        return result == 1 ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.UPDATE_ZONE_ERROR);
    }

    @Transactional
    @DeleteMapping("deleteArea")
    @ApiOperation(value = "删除大区信息", notes = "可以批量删除，若大区下存在子分区则不许删除")
    public String deleteArea(@RequestParam(name = "ids", required = true) Integer[] ids) {
        int result = 0;
        if (ids != null && ids.length > 0)
            result = areaService.deleteAreas(ids);
        return result == ids.length ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.DELETE_ZONE_ERROR);
    }



}
