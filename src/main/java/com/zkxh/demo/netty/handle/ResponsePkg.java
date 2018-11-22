package com.zkxh.demo.netty.handle;


import com.zkxh.demo.netty.data.request.RequestData;
import com.zkxh.demo.netty.utils.ConstantValue;
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
    public byte[] dataResponseVoice(RequestData msg) {

        int len = msg.getLength();
        byte[] resp = new byte[len];
        byte[] type = NettyDataUtils.intToByteArray(msg.getType());
        resp[0] = type[2];
        resp[1] = type[3];
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
        byte[] length = NettyDataUtils.intToByteArray(msg.getLength());
        resp[14] = length[2];
        resp[15] = length[3];

        byte[] cmd = NettyDataUtils.intToByteArray(msg.getCmd());
        resp[16] = cmd[2];
        resp[17] = cmd[3];
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
        resp[26] = msg.getResult();

        resp[27] = (byte) 0x00;

        byte[] ndName = NettyDataUtils.intToByteArray(msg.getNdName());
        resp[28] = ndName[2];
        resp[29] = ndName[3];

        byte[] body = msg.getBody();
        System.arraycopy(body, 0, resp, 30, body.length);

        System.out.println("返回数据包内容：");
        for (int i = 0; i < len; i++) {
            System.out.printf(" 0x%02x ", resp[i]);
        }
        System.out.println("\n返回结束");
        return resp;
    }


    /**
     * @param [msg, code]
     * @return byte[]
     * @description 封装响应数据的  方法  数据帧头 +  数据体的 code
     * @date 16:09 2018/9/28
     * @auther lifeng
     **/
    public byte[] dataResponse(RequestData msg) {

//        int len = msg.getLength();  //返回终端 TODO 语音下发  ，， 返回加部门和分组
        //返回封装 返回心跳结果
        if (msg.getNdName() == ConstantValue.MSG_BODY_NODE_NAME_HEARTBEAT) {
            byte[] resp = new byte[30];
            byte[] type = NettyDataUtils.intToByteArray(msg.getType());
            resp[0] = type[2];
            resp[1] = type[3];
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
            byte[] length = NettyDataUtils.intToByteArray(msg.getLength());
            resp[14] = length[2];
            resp[15] = length[3];


            byte[] cmd = NettyDataUtils.intToByteArray(msg.getCmd());
            resp[16] = cmd[2];
            resp[17] = cmd[3];
            byte[] seq = NettyDataUtils.intToByteArray(msg.getSequenceId());
            resp[18] = seq[2];
            resp[19] = seq[3];

            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);//获取年份
            int rty = year - 2000;
            int month = cal.get(Calendar.MONTH) + 1;//获取月份
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
            resp[26] = msg.getResult();

            resp[27] = (byte) 0x00;

            byte[] ndName = NettyDataUtils.intToByteArray(msg.getNdName());
            resp[28] = ndName[2];
            resp[29] = ndName[3];
            for (int i = 0; i < resp.length; i++)
                System.out.printf(" 0x%02x", resp[i]);
            return resp;
        }
        //封装 返回 气体信息 收到结果
        if (msg.getNdName() == ConstantValue.MSG_BODY_NODE_NAME_SENSOR_DATA) {
            byte[] resp = new byte[msg.getLength()];
            byte[] type = NettyDataUtils.intToByteArray(msg.getType());
            resp[0] = type[2];
            resp[1] = type[3];
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
            byte[] length = NettyDataUtils.intToByteArray(30);
            resp[14] = length[2];
            resp[15] = length[3];

            byte[] cmd = NettyDataUtils.intToByteArray(msg.getCmd());
            resp[16] = cmd[2];
            resp[17] = cmd[3];
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
            resp[26] = msg.getResult();

            resp[27] = msg.getNodeCount();

            byte[] ndName = NettyDataUtils.intToByteArray(msg.getNdName());
            resp[28] = ndName[2];
            resp[29] = ndName[3];
            for (int i = 0; i < resp.length; i++)
                System.out.printf(" 0x%02x", resp[i]);
            return resp;
        }
        //返回 上传的语音收到结果
        if (msg.getNdName() == ConstantValue.MSG_HEADER_COMMAND_ID_NULL) {
            byte[] resp = new byte[30];
            byte[] type = NettyDataUtils.intToByteArray(msg.getType());
            resp[0] = type[2];
            resp[1] = type[3];
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
            byte[] length = NettyDataUtils.intToByteArray(msg.getLength());
            resp[14] = length[2];
            resp[15] = length[3];


            byte[] cmd = NettyDataUtils.intToByteArray(msg.getCmd());
            resp[16] = cmd[2];
            resp[17] = cmd[3];
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
            resp[26] = msg.getResult();

            resp[27] = (byte) 0x00;

            byte[] ndName = NettyDataUtils.intToByteArray(msg.getNdName());
            resp[28] = ndName[2];
            resp[29] = ndName[3];

            for (int i = 0; i < resp.length; i++)
                System.out.printf(" 0x%02x", resp[i]);
            return resp;
        }


        //向下  终端发送语音信息 数据封装
        if (msg.getCmd() == ConstantValue.MSG_HEADER_COMMAND_ID_SEND_VOICE) {
            int len = msg.getLength();
            byte[] resp = new byte[len];
            byte[] type = NettyDataUtils.intToByteArray(msg.getType());
            resp[0] = type[2];
            resp[1] = type[3];
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
            byte[] length = NettyDataUtils.intToByteArray(len);
            resp[14] = length[2];
            resp[15] = length[3];


            byte[] cmd = NettyDataUtils.intToByteArray(msg.getCmd());
            resp[16] = cmd[2];
            resp[17] = cmd[3];
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
            resp[26] = msg.getResult();

            resp[27] = (byte) 0x00;

            byte[] ndName = NettyDataUtils.intToByteArray(msg.getNdName());
            resp[28] = ndName[2];
            resp[29] = ndName[3];

            byte[] count = NettyDataUtils.intToByteArray(msg.getCount());
            resp[30] = count[2];
            resp[31] = count[3];

            byte[] body = msg.getBody();

            System.arraycopy(body, 0, resp, 32, (body.length));
            return resp;
        }
        return null;
    }
}
