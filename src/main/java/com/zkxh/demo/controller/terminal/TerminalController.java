package com.zkxh.demo.controller.terminal;

import com.zkxh.demo.common.enums.ResultEnum;
import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.model.terminal.StaffTerminal;
import com.zkxh.demo.service.terminal.TerminalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TerminalController
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/25 11:19
 * @Vserion v0.0.1
 */
@RestController
@Api(value = "TerminalController", tags = {"终端操作接口"})
public class TerminalController {


    @Resource
    private TerminalService terminalService;


    @ApiOperation(value = "录入终端信息", notes = "录入终端信息")
    @PostMapping("terminal/addTerminal")
    public String insertTerminal(@RequestBody StaffTerminal staffTerminal) {
        int i = terminalService.addTerminal(staffTerminal);
        return i == 1 ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.FAILED);
    }

    @ApiOperation(value = "获取所有终端", notes = "获取终端")
    @GetMapping("terminal/getAll")
    public String getAllTerminal() {
        List<StaffTerminal> list = terminalService.getAllTerminal();
        return list.size() > 0 ? ResultUtil.jsonToStringSuccess(list) : ResultUtil.jsonToStringError(ResultEnum.DATA_NOT_FOUND);
    }

    @ApiOperation(value = "获取所有未绑定终端", notes = "获取所有未绑定终端")
    @GetMapping("terminal/getNotBinDingTerminals")
    public String getNotBinDingTerminals() {
        List<StaffTerminal> list = terminalService.getNotBinDingTerminals();
        return list.size() > 0 ? ResultUtil.jsonToStringSuccess(list) : ResultUtil.jsonToStringError(ResultEnum.DATA_NOT_FOUND);
    }

    @ApiOperation(value = "解除绑定", notes = "如果staffId为空则认为是接触绑定，若不为空认为是解除绑定同时，绑定新的用户")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "java.lang.Integer", name = "staffId", value = "员工ID", required = false),
            @ApiImplicitParam(dataType = "java.lang.Integer", name = "staffTerminalId", value = "终端数据库中的ID", required = true)
    })
    @PutMapping("terminal/unBindAndBinding/{staffId}")
    public String unBindAndBinding(@PathVariable(name = "staffId", required = false) Integer staffId, @RequestParam(name = "staffTerminalId", required = true) Integer staffTerminalId) {
        boolean flag = false;
        if (staffId == null)
            //接触绑定
            flag = terminalService.unBind(staffTerminalId);
        else
            flag = terminalService.unBindAndBinding(staffId, staffTerminalId);
        return flag == true ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.FAILED);
    }


}
