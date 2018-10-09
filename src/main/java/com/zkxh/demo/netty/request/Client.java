package com.zkxh.demo.netty.request;

import com.zkxh.demo.netty.data.response.ResponseData;
import io.netty.channel.Channel;

/**
 * @ClassName Client
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/28 16:40
 * @Vserion v0.0.1
 */

public class Client {

    public static int sendCmd(ResponseData responseData) {

        String t_ip = responseData.getCustomMsg().getStationIp();

        StringBuffer stringBuffer = new StringBuffer("192.168.");
        stringBuffer.append(t_ip);
        String ip = stringBuffer.toString();
        Channel channel = ChannelMap.getChannelByName(ip);
        channel.write(responseData);
        channel.flush();

        return 0;
    }

}
