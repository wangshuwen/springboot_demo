package com.zkxh.demo.controller.base_station;

import com.zkxh.demo.common.result.ResultUtil;
import com.zkxh.demo.netty.data.request.RequestData;
import com.zkxh.demo.netty.data.response.ResponseData;
import com.zkxh.demo.netty.request.Client;
import com.zkxh.demo.netty.utils.NettyDataUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.zkxh.demo.socket.SocketCode.MSG_HEADER_FREAME_HEAD;

/**
 * @author wangshuwen
 * @Description:
 * @Date 2018/11/26/14:51
 */
@RestController
public class BaseStationSettingController {

    @GetMapping("/setting")
    public String settingBaseStation(@RequestParam("ip") String ip, @RequestParam("network") String network) {
        System.out.println("setting大苏打的");
        System.out.println("4233333333333333333333333333333333333333333333333333333");
        System.out.println("setting大苏打的");
        System.out.println("4233333333333333333333333333333333333333333333333333333");
        System.out.println("4233333333333333333333333333333333333333333333333333333");
        System.out.println("4233333333333333333333333333333333333333333333333333333");
        System.out.println("4233333333333333333333333333333333333333333333333333333");
        System.out.println("setting大苏打的");
        System.out.println("4233333333333333333333333333333333333333333333333333333");
        System.out.println(ip);
        System.out.println(network);

        ResponseData responseData = new ResponseData();

        RequestData requestData = new RequestData();
        requestData.setType(MSG_HEADER_FREAME_HEAD);
        requestData.setLength(38);
        requestData.setCmd(18);
        requestData.setSequenceId(45);
        requestData.setNdName(8198);
        requestData.setTerminalIp("1.1");
        requestData.setStationIp("1.1");

        //封装ip和子网掩码 8字节
        byte[] body = new byte[8];
        String[] ips = ip.split("\\.");
        System.out.println("长度"+ips.length);
        for (String s : ips) {
            System.out.println(s);
        }
        System.out.println("esdfdssfdsfddssfddsf"+ips.toString());
        String[] nets = network.split("\\.");
        body[0] = NettyDataUtils.intToByteArray(Integer.parseInt(ips[0]))[3];
        body[1]= NettyDataUtils.intToByteArray(Integer.parseInt(ips[1]))[3];
        body[2]=NettyDataUtils.intToByteArray(Integer.parseInt(ips[2]))[3];
        body[3]=NettyDataUtils.intToByteArray(Integer.parseInt(ips[3]))[3];
        body[4]=NettyDataUtils.intToByteArray(Integer.parseInt(nets[0]))[3];
        body[5]=NettyDataUtils.intToByteArray(Integer.parseInt(nets[1]))[3];
        body[6]=NettyDataUtils.intToByteArray(Integer.parseInt(nets[2]))[3];
        body[7]=NettyDataUtils.intToByteArray(Integer.parseInt(nets[3]))[3];
        requestData.setBody(body);
        System.out.println();
        for (byte b : body) {
            System.out.printf("0x%02x ",b);
        }
        System.out.println();
        responseData.setCustomMsg(requestData);

          Client.sendCmd(responseData);

        return ResultUtil.jsonToStringSuccess(responseData);
    }
}
