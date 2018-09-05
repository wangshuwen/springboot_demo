package com.zkxh.demo.socket.client;

import java.util.Date;

/**
 * @ClassName SendCommand
 * @Description
 * @Auther lifeng
 * @DATE 2018/8/21 18:48
 * @Vserion v0.0.1
 */

public class SendCommand {

    /**
     * @param [date, ips]
     * @return int
     * @description 发送校准时间 方法
     * @date 2018/8/21
     * @auther lifeng
     **/
    public int sendConfirmTime(Date date, String... ips) {
        return 0;
    }

    /**
     * @param [type, ips]
     * @return int
     * @description 发送 获取终端 数据信息的 方法
     * @date 18:52 2018/8/21
     * @auther lifeng
     **/
    public int sendGetInfo(int type, String... ips) {
        return 0;
    }


    /**
     * @param [filePath, ips]
     * @return int
     * @description 发送所有的时间信息
     * @date 18:53 2018/8/21
     * @auther lifeng
     **/
    public int sendVoice(String filePath, String... ips) {
        return 1;
    }


}
