package com.zkxh.demo.controller.base_station;

import com.github.pagehelper.PageInfo;
import com.zkxh.demo.common.base.controller.impl.BaseController;
import com.zkxh.demo.common.base.log.BaseLog;
import com.zkxh.demo.common.enums.ResultEnum;
import com.zkxh.demo.common.handle.ErrorCode;
import com.zkxh.demo.common.handle.RuntimeWebException;
import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.common.util.convert.DateConvert;
import com.zkxh.demo.common.util.string.StringUtils;
import com.zkxh.demo.model.base_station.BaseStation;
import com.zkxh.demo.service.base_station.BaseStationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName BaseStationController
 * @Description
 * @Auther lifeng
 * @DATE 2018/10/26 16:04
 * @Vserion v0.0.1
 */
@RestController
@Api(value = "BaseStationController", tags = {"基站基础信息操作"})
public class BaseStationController extends BaseController {

    @Resource
    BaseStationService baseStationService;

    @PostMapping("station/add")
    @ApiOperation(value = "录入基站信息", notes = "默认为未工作状态")
    public String addBaseStation(@RequestBody BaseStation station) {
        station.setCreateTime(new Date());
        int result = baseStationService.addBaseStation(station);
        return result == 1 ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.ADD_STATION_FAILE);
    }

    @GetMapping("station/find/{begin},{end}")
    @ApiOperation(value = "查询基站信息", notes = "根据开始和结束时间获取基站的基本信息")
    public String findStationInfoByTime(@PathVariable(name = "begin") String begin
            , @PathVariable(name = "end") String end
            , @RequestParam(name = "startPage", defaultValue = "1", required = false) Integer startPage
            , @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {

        PageInfo<BaseStation> pageInfo = null;
        logger.info("" + end);
        if (!StringUtils.checkNull(begin) && !StringUtils.checkNull(end)) {
            pageInfo = baseStationService.findBaseStationInfo(DateConvert.convertStampToDateString(begin, 10), DateConvert.convertStampToDateString(end, 10), startPage, pageSize);
        }
        if (StringUtils.checkNull(begin) && StringUtils.checkNull(end)) {
            pageInfo = baseStationService.findAllBaseStationInfo(startPage, pageSize);
        }
        if (StringUtils.checkNull(begin) && !StringUtils.checkNull(end)) {
            pageInfo = baseStationService.findBaseStationInfoByEnd(DateConvert.convertStampToDateString(end, 10), startPage, pageSize);
        }
        if (!StringUtils.checkNull(begin) && StringUtils.checkNull(end)) {
            pageInfo = baseStationService.findBaseStationInfoByStart(DateConvert.convertStampToDateString(begin, 10), startPage, pageSize);
        }
        return ResultUtil.jsonToStringSuccess(pageInfo);
    }

    @PutMapping("station/update")
    @ApiOperation(value = "更新基站信息", notes = "通过基站的ID ，更新基站的基本新")
    public String updateStationInfo(@RequestParam BaseStation station) {
        station.setUpdateTime(new Date());
        return baseStationService.updateStationInfo(station) == 1 ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.UPDATE_INFO_ERROR);
    }

    @ApiOperation(value = "删除基站信息", notes = "通过ID删除")
    @DeleteMapping("station/delete/{id}")
    public String deleteStation(@PathVariable("id") Integer id) {
        return baseStationService.deleteStationById(id) == 1 ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.DELETE_STATION_ERROR);
    }

    @Transactional
    @DeleteMapping("station/delete")
    @ApiOperation(value = "批量删除基站信息", notes = "参数为id的数组，以逗号形式拼接")
    public String deleteStationByIds(@RequestParam Integer[] ids) {
        int len = ids.length;
        return baseStationService.deleteStationByIds(ids) == len ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.DELETE_STATION_ERROR);
    }


}
