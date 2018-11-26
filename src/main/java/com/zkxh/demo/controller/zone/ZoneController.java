package com.zkxh.demo.controller.zone;

import com.zkxh.demo.common.enums.ResultEnum;
import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.model.zone.Zone;
import com.zkxh.demo.service.zone.ZoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @GetMapping("getZones/{zoneName}")
    @ApiOperation(value = "获取所有矿下大区", notes = "大区信息，以及下属的基站数量，做到分页查询")
    public String getZone(@RequestParam(name = "page", defaultValue = "1", required = false) Integer startPage,
                          @RequestParam(name = "limit", defaultValue = "10", required = false) Integer pageSize,
                          @PathVariable(name = "zoneName", required = false) String zoneName) {
        zoneService.findAllZoneByParam();
        return ResultUtil.jsonToStringSuccess();
    }


    @PostMapping("addZone")
    @ApiOperation(value = "添加所有区域信息")
    public String addZone(@RequestBody List<Zone> zone) {
        int result = zoneService.addZone(zone);
        return result == 1 ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.ADD_ZONE_ERROR);
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
