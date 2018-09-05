package com.zkxh.demo.socket.client;

import java.util.Date;

/**
 * @ClassName CommandSet
 * @Description
 * @Auther lifeng
 * @DATE 2018/8/21 18:33
 * @Vserion v0.0.1
 */

public class CommandSet {


    /**
     * 将id 的字符串转换为 字节数组
     *
     * @param ip
     * @return
     */
    private byte[] ipStringToIntArray(String ip) {
        byte[] ipArr = new byte[4];

        int dotIndex0 = ip.indexOf(".");
        if (dotIndex0 == -1 || dotIndex0 == 0) {
            return null;
        }
        ipArr[0] = (byte) Integer.parseInt(ip.substring(0, dotIndex0));

        int dotIndex1 = ip.indexOf(".", dotIndex0 + 1);
        if (dotIndex1 == -1 || dotIndex1 <= dotIndex0) {
            return null;
        }
        ipArr[1] = (byte) Integer.parseInt(ip.substring(dotIndex0 + 1,
                dotIndex1));

        int dotIndex2 = ip.indexOf(".", dotIndex1 + 1);
        if (dotIndex2 == -1 || dotIndex2 <= dotIndex1
                || dotIndex2 == (ip.length() - 1)) {
            return null;
        }
        ipArr[2] = (byte) Integer.parseInt(ip.substring(dotIndex1 + 1,
                dotIndex2));

        ipArr[3] = (byte) Integer.parseInt(ip.substring(dotIndex2 + 1));

        return ipArr;
    }

    /**
     * @param [date, ip] 时间和将要设定的Ip
     * @return byte[]
     * @description 设置时间，
     * @date 18:43 2018/8/21
     * @auther lifeng
     **/
    byte[] setTirmalDateTimePkg(Date date, String ip) {
        //定义 命令数据 包长度
        byte[] pkg = new byte[24];

        //帧头

        //数据体

        //校验码

        //帧尾


        return pkg;
    }


    /**
     * @param [infoType, ip]  数据信息类型 温湿度，co，o2，ch4，等，IP 为终端IP
     * @return byte[]
     * @description 设置 获取信息命令。
     * @date 18:39 2018/8/21
     * @auther lifeng
     **/
    byte[] setGetInfoPkg(int infoType, String ip) {
        byte[] pkg = new byte[24];

        //帧头

        //数据体

        //校验码

        //帧尾

        return pkg;
    }

    /**
     * @param [voice] 读取声音文件缓冲的512长度
     * @return byte[]
     * @description 将音频文件传输到
     * @date 18:45 2018/8/21
     * @auther lifeng
     **/
    byte[] setVoicePkg(String voice) {
        byte[] pkg = new byte[512];

        pkg = voice.getBytes();

        return pkg;
    }


}
