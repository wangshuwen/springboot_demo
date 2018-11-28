package com.zkxh.demo.controller.teminal_road;

import com.zkxh.demo.common.enums.ResultEnum;
import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.model.base_station.BaseStation;
import com.zkxh.demo.model.teminal_road.TeminalRoad;
import com.zkxh.demo.service.teminal_road.TeminalRoadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangshuwen
 * @Description:
 * @Date 2018/11/28/17:01
 */
@Controller
@RequestMapping("teminalRoad/")
@Api(value = "TeminalRoadController", tags = {"终端路线基础信息操作"})
public class TeminalRoadController {
    @Resource
    private TeminalRoadService teminalRoadService;
    @GetMapping("findListByTeminalId")
    @ApiOperation(value = "终端路线信息", notes = "根据终端的所以路线信息")
    public String findListByTeminalId(@RequestParam("teminalId") int teminalId) {
        List<TeminalRoad> list = teminalRoadService.findListByTeminalId(teminalId);
        return list.size()>0 ? ResultUtil.jsonToStringSuccess(list) : ResultUtil.jsonToStringError(ResultEnum.DATA_NOT_FOUND);
    }


}
