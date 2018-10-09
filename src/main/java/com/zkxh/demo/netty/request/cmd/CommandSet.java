package com.zkxh.demo.netty.request.cmd;

import com.zkxh.demo.netty.data.request.RequestData;

/**
 * @ClassName CommandSet
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/28 16:43
 * @Vserion v0.0.1
 */

public class CommandSet {

    public byte[] query(RequestData requestData, byte code) {

        byte[] req = new byte[27];
//        req[0] = (byte) 0x66;
//        req[1] = (byte) 0x88;
//        byte[] tId = NettyDataUtils.intToByteArray(requestData.getTerminalId());
//        req[2] = tId[0];
//        req[3] = tId[1];
//        req[4] = tId[2];
//        req[5] = tId[3];
//        byte[] sId = NettyDataUtils.intToByteArray(requestData.getStationId());
//        req[6] = sId[0];
//        req[7] = sId[1];
//        req[8] = sId[2];
//        req[9] = sId[3];
//        req[10] = (byte) requestData.getTerminalIp1();
//        req[11] = (byte) requestData.getTerminalIp2();
//        req[12] = (byte) requestData.getStationIp1();
//        req[13] = (byte) requestData.getStationIp2();
//        req[14] = (byte) 0x00;
//        req[15] = (byte) 0x1b;
//
//        byte[] cmd = NettyDataUtils.intToByteArray(requestData.getCmd());
//        req[16] = cmd[0];
//        req[17] = cmd[1];
//
//        byte[] seq = NettyDataUtils.intToByteArray(requestData.getSequenceId());
//
//        req[18] = seq[2];
//        req[19] = seq[3];
//
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);//获取年份
//        int rty = year - 2000;
//        int month = cal.get(Calendar.MONTH);//获取月份
//        int day = cal.get(Calendar.DATE);//获取日
//        int hour = cal.get(Calendar.HOUR);//小时
//        int minute = cal.get(Calendar.MINUTE);//分
//        int second = cal.get(Calendar.SECOND);//秒
//
//        req[20] = (byte) rty;
//        req[21] = (byte) month;
//        req[22] = (byte) day;
//        req[23] = (byte) hour;
//        req[24] = (byte) minute;
//        req[25] = (byte) second;
//        req[26] = code;
//
        return req;

    }
}
