package com.zkxh.demo.controller.user;

import com.zkxh.demo.common.base.controller.impl.BaseController;
import com.zkxh.demo.common.da.kafka.KafkaSender;
import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.model.user.SysUser;
import com.zkxh.demo.websocket.WSServer;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

/**
 * @ClassName UserController
 * @Description
 * @Auther lifeng
 * @DATE 2018/8/16 15:51
 * @Vserion v0.0.1
 */
@Api(value = "UserController", tags = {"用户操作接口"})
@RestController
public class UserController extends BaseController {

    @Autowired
    private KafkaSender<SysUser> kafkaSender;

    @GetMapping("tt")
    public String kafkaSend() throws InterruptedException {
        //模拟发消息
        for (int i = 0; i < 5; i++) {

            SysUser user = new SysUser();
            user.setSysHeadimg("" + i);
            user.setCreateBy(1);
            user.setSysLastLoginTime(new Date());

            kafkaSender.send(user);
            Thread.sleep(3000);

        }
        return ResultUtil.jsonToStringSuccess();
    }

    @GetMapping("pushVideoListToWeb")
    public Map<String, Object> pushVideoListToWeb() {
        Map<String, Object> result = new HashMap<String, Object>();

        try {
            WSServer.sendInfo("jspn:{a:a:a:a:A:a:a::A:A:A:}");
            result.put("operationResult", true);
        } catch (IOException e) {
            result.put("operationResult", true);
        }
        return result;
    }

}





