package com.zkxh.demo.netty.request;

import com.zkxh.demo.netty.data.response.ResponseData;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * @ClassName Client
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/28 16:40
 * @Vserion v0.0.1
 */
@Component
public class Client {

    public static int sendCmd(ResponseData responseData) {

        String t_ip = responseData.getCustomMsg().getStationIp();

        StringBuffer stringBuffer = new StringBuffer("192.168.");
        stringBuffer.append(t_ip);
        String ip = stringBuffer.toString();
        Channel channel = ChannelMap.getChannelByName(ip);
        channel.writeAndFlush(responseData);

//        channel.write(responseData);
//        channel.flush();
        return 0;
    }

    public static int sendCmd(byte[] bytes) {


//        ChannelMap.getChannelNum();
//        System.out.println( ChannelMap.getChannelNum());
        Channel channel = ChannelMap.getChannelByName("192.168.1.101");
        channel.writeAndFlush(bytes);

//        channel.write(responseData);
//        channel.flush();
        return 0;
    }

}
