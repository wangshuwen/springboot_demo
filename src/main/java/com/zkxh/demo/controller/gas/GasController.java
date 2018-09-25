package com.zkxh.demo.controller.gas;

import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.service.gas.GasInfoService;
import com.zkxh.demo.vo.resp.GasSearchRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName GasController
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/21 15:59
 * @Vserion v0.0.1
 */


@RequestMapping("gas/")
@RestController
@Api(value = "GasController", tags = {"气体信息操作接口"})
public class GasController {

    @Resource
    private GasInfoService gasInfoService;

    @ApiOperation(value = "根据 员工姓名 查询环境信息 接口", notes = "根据员工姓名的进行模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "java.lang.String", name = "staffName", value = "员工姓名", required = true),
            @ApiImplicitParam(dataType = "java.lang.Integer", name = "pageSize", value = "每个页面显示的数据行数", required = false),
            @ApiImplicitParam(dataType = "java.lang.Integer", name = "startPage", value = "起始页", required = false)
    })
    @GetMapping("findRtGasInfoByStaffName")
    public String getRtGasInfoByStaffName(@RequestParam(required = true) String staffName, @RequestParam(required = false, defaultValue = "8") Integer pageSize, @RequestParam(required = false, defaultValue = "1") Integer startPage) {

        String result = gasInfoService.findGasInfoByStaffName(staffName, startPage, pageSize);

        return ResultUtil.jsonToStringSuccess(result);
    }

}
