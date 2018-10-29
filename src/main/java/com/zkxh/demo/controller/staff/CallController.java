package com.zkxh.demo.controller.staff;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zkxh.demo.common.enums.ResultEnum;
import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.common.util.ClazzUtil;
import com.zkxh.demo.model.user.SysUser;
import com.zkxh.demo.netty.data.request.RequestData;
import com.zkxh.demo.netty.utils.ConstantValue;
import com.zkxh.demo.service.call.CallService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * @ClassName CallController
 * @Description
 * @Auther lifeng
 * @DATE 2018/10/10 17:43
 * @Vserion v0.0.1
 */
@RestController
@Api(value = "CallController", tags = {"员工实时对讲接口"})
public class CallController {


    @Resource
    private CallService callService;


    /**
     * @param [wavFile, staffId]
     * @return java.lang.String
     * @description 呼叫
     * @date 2018/10/24
     * @auther lifeng
     **/
    @PostMapping("staff/call")
    @ApiOperation(value = "对终端进行呼叫", notes = "通过语音数据和矿下员工ID 进行语音通信")
    public String callStaff(MultipartFile wavFile, Integer staffId) {
        Object sysUser = SecurityUtils.getSubject().getPrincipal();
        ClazzUtil clazzUtil = new ClazzUtil();
        Integer userId = (Integer) clazzUtil.getFieldValueByName("sysUserid", sysUser);
        boolean result = false;
        if (wavFile != null && staffId != null) {
//           result = callService.callStaffByStaffId(wavFile,staffId,userId);
//           return result ? ResultUtil.jsonToStringSuccess(): ResultUtil.jsonToStringError(ResultEnum.SEND_VOICE_ERROR);
            callService.callStaffByStaffId(wavFile, staffId, userId);
            return ResultUtil.jsonToStringSuccess();
        }
        return ResultUtil.jsonToStringError(ResultEnum.SEND_VOICE_ERROR);
    }


    @ApiOperation(value = "对终端进行在线监检查", notes = "确定终端是否连接网络")
    @GetMapping("checkTerminalOnline")
    public String checkTerminalIsNotOnline(Integer staffId) {
        boolean flag = callService.pingTerminal(staffId);
        if (flag) {
            return ResultUtil.jsonToStringSuccess();
        }
        return ResultUtil.jsonToStringError(ResultEnum.CHECK_ONLINE_FAILE);
    }


}
