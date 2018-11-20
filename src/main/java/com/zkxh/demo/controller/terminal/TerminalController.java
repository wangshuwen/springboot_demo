package com.zkxh.demo.controller.terminal;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zkxh.demo.common.enums.ResultEnum;
import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.model.terminal.StaffTerminal;
import com.zkxh.demo.service.terminal.TerminalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @GetMapping("terminal/checkExist")
    @ApiOperation(value = "检验终端ID是否存在", notes = "根据终端ID 判断是否已经存在是数据库中，保证terminalId 唯一")
    public String checkTerminalIdExist(@RequestParam(name = "terminalId") Integer terminalId) {
        boolean flag = terminalService.checkTerminalExist(terminalId);
        return ResultUtil.jsonToStringSuccess(flag);
    }

    @PutMapping("terminal/update")
    @ApiOperation(value = "更新终端信息", notes = "通过终端的ID更新终端信息，ID不可以更新，")
    public String updateTerminalInfo(@RequestBody StaffTerminal staffTerminal) {
        int result = terminalService.updateTerminalByTerminalId(staffTerminal);
        return result == 1 ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.FAILED);
    }

    @Transactional
    @ApiOperation(value = "终端信息删除", notes = "单个删除终端，")
    @DeleteMapping("terminal/delete/{terminalId}")
    public String deleteTerminalById(@PathVariable(name = "terminalId", required = true) Integer terminalId) {
        Integer[] ids = new Integer[1];
        ids[0] = terminalId;
        int result = terminalService.deleteTerminalByTerminalId(ids);
        return result == 1 ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.FAILED);
    }

    @DeleteMapping("terminal/delete")
    @ApiOperation(value = "批量删除终端信息", notes = "参数为拼接数组形式，")
    public String deleteTerminalByIds(@RequestParam(name = "ids") Integer[] ids) {
        int len = ids.length;
        int result = terminalService.deleteTerminalByTerminalId(ids);
        return len == result ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.FAILED);
    }

    @GetMapping("terminal/getTerminalByParams")
    @ApiOperation(value = "分页获取所有终端信息", notes = "分页获取所有终端信息，若终端ID传入为 null或 '' ,则认为获取所有终端信息，")
    public String findTerminalByParams(@RequestParam(name = "limit", defaultValue = "10", required = false) Integer pageSize
            , @RequestParam(name = "page", defaultValue = "1", required = false) Integer startPage
            , @RequestParam(name = "terminalId", required = false) Integer terminalId) {
        Page result = terminalService.findTerminalInfoByParams(startPage, pageSize, terminalId);
        PageInfo pageInfo = new PageInfo(result);
        return pageInfo.getSize() > 0 ? ResultUtil.jsonToStringSuccess(pageInfo) : ResultUtil.jsonToStringError(ResultEnum.DATA_NOT_FOUND);
    }


    @ApiOperation(value = "录入终端信息", notes = "录入终端信息")
    @PostMapping("terminal/addTerminal")
    public String insertTerminal(@RequestBody StaffTerminal staffTerminal) {
        int i = terminalService.addTerminal(staffTerminal);
        return i == 1 ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.FAILED);
    }

    @Transactional
    @ApiOperation(value = "批量录入终端信息", notes = "录入终端信息")
    @PostMapping("terminal/addTerminals")
    public String insertTerminals(@RequestBody List<StaffTerminal> staffTerminals) {
        int len = staffTerminals.size();
        int i = 0;
        for (StaffTerminal item : staffTerminals) {
            if (terminalService.addTerminal(item) == 1)
                i++;
        }
        return i == len ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.FAILED);

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
            @ApiImplicitParam(dataType = "java.lang.Integer", name = "staffTerminalId", value = "终端数据库中的ID", required = true)
    })
    @PutMapping("terminal/unBind/{staffTerminalId}")
    public String unBind(@PathVariable(name = "staffTerminalId", required = false) Integer staffTerminalId) {
        boolean flag = false;
        if (staffTerminalId != null)
            //解除绑定
            flag = terminalService.unBind(staffTerminalId);
        return flag == true ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.FAILED);
    }


    @ApiOperation(value = "绑定新终端", notes = "，，")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "java.lang.Integer", name = "staffId", value = "员工ID", required = true),
            @ApiImplicitParam(dataType = "java.lang.Integer", name = "staffTerminalId", value = "终端数据库中的ID", required = true)
    })
    @PutMapping("terminal/binding/{staffId}")
    public String binding(@PathVariable(name = "staffId", required = false) Integer staffId, @RequestParam(name = "staffTerminalId", required = true) Integer staffTerminalId) {
        boolean flag = false;
        if (staffId != null && staffTerminalId != null)
            flag = terminalService.binding(staffId, staffTerminalId);
        return flag == true ? ResultUtil.jsonToStringSuccess() : ResultUtil.jsonToStringError(ResultEnum.FAILED);
    }


}
