package com.zkxh.demo.controller.zone;

import com.zkxh.demo.common.result.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ZoneController
 * @Description
 * @Auther lifeng
 * @DATE 2018/11/22 10:39
 * @Vserion v0.0.1
 */
@RestController
@Api(value = "ZoneController", tags = "矿下区域操作")
public class ZoneController {


    @GetMapping("getLargeAreas")
    public String getLargeAreas() {
        return ResultUtil.jsonToStringSuccess();
    }

}
