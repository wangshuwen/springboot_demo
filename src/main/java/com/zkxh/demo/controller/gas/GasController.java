package com.zkxh.demo.controller.gas;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zkxh.demo.common.enums.ResultEnum;
import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.service.gas.GasInfoService;
import com.zkxh.demo.vo.resp.GasWSRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    public String getRtGasInfoByStaffName(@RequestParam(required = true) String staffName,
                                          @RequestParam(required = false, defaultValue = "8", name = "limit") Integer pageSize,
                                          @RequestParam(required = false, defaultValue = "1", name = "page") Integer startPage) {
        Page page = gasInfoService.findGasInfoByStaffName(staffName, startPage, pageSize);
        PageInfo pageInfo = new PageInfo(page);
        return ResultUtil.jsonToStringSuccess(pageInfo);
    }


    @ApiOperation(value = "获取最近的气体信息", notes = "解决数据开始空白问题")
    @GetMapping("getRecentlyGasInfo/{num}")
    public String getRecentlyGasInfo(@PathVariable(name = "num", required = false) Integer number) {
        if (null != number && 0 != number) {
            List<GasWSRespVO> list = gasInfoService.findGasInfoLastTenRecords(number);
            return ResultUtil.jsonToStringSuccess(list);
        }
        return ResultUtil.jsonToStringError(ResultEnum.DATA_NOT_FOUND);
    }

}
