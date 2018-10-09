package com.zkxh.demo.netty.handle;


import com.zkxh.demo.netty.data.request.RequestData;
import com.zkxh.demo.netty.utils.NettyDataUtils;

import java.util.Calendar;

/**
 * @ClassName Response
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/28 13:20
 * @Vserion v0.0.1
 */

public class ResponsePkg {


    /**
     * @param [msg, code]
     * @return byte[]
     * @description 封装响应数据的  方法  数据帧头 +  数据体的 code
     * @date 16:09 2018/9/28
     * @auther lifeng
     **/
    public byte[] dataResponse(RequestData msg, byte code) {

        //  byte[] body = msg.getBody();

        byte[] resp = new byte[27];
        resp[0] = (byte) 0x66;
        resp[1] = (byte) 0x88;
        byte[] tId = NettyDataUtils.intToByteArray(msg.getTerminalId());
        resp[2] = tId[0];
        resp[3] = tId[1];
        resp[4] = tId[2];
        resp[5] = tId[3];
        byte[] sId = NettyDataUtils.intToByteArray(msg.getStationId());
        resp[6] = sId[0];
        resp[7] = sId[1];
        resp[8] = sId[2];
        resp[9] = sId[3];
        resp[10] = (byte) msg.getTerminalIp1();
        resp[11] = (byte) msg.getTerminalIp2();
        resp[12] = (byte) msg.getStationIp1();
        resp[13] = (byte) msg.getStationIp2();
        resp[14] = (byte) 0x00;
        resp[15] = (byte) 0x1b;


        byte[] cmd = NettyDataUtils.intToByteArray(msg.getCmd());
        resp[16] = cmd[0];
        resp[17] = cmd[1];
        byte[] seq = NettyDataUtils.intToByteArray(msg.getSequenceId());

        resp[18] = seq[2];
        resp[19] = seq[3];

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);//获取年份
        int rty = year - 2000;
        int month = cal.get(Calendar.MONTH);//获取月份
        int day = cal.get(Calendar.DATE);//获取日
        int hour = cal.get(Calendar.HOUR);//小时
        int minute = cal.get(Calendar.MINUTE);//分
        int second = cal.get(Calendar.SECOND);//秒

        resp[20] = (byte) rty;
        resp[21] = (byte) month;
        resp[22] = (byte) day;
        resp[23] = (byte) hour;
        resp[24] = (byte) minute;
        resp[25] = (byte) second;
        resp[26] = code;

        return resp;
    }


//    public static void main(String[] args) {
//        RequestData customMsg = new RequestData();
//        customMsg.setTerminalId(1);
//        customMsg.setStationId(1);
//        customMsg.setTerminalIp1(1);
//        customMsg.setTerminalIp2(251);
//        customMsg.setStationIp1(1);
//        customMsg.setStationIp2(1);
//        customMsg.setSequenceId(123);
//
//        ResponsePkg response = new ResponsePkg();
//
//        for (byte s : response.dataResponse(customMsg, (byte) 0x55)) {
//            System.out.printf("0x%02x ", s);
//        }
//        System.out.println("\n==============");
//    }


}
