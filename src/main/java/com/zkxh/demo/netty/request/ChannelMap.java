package com.zkxh.demo.netty.request;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName ChannelMap
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/28 16:31
 * @Vserion v0.0.1
 */

public class ChannelMap {


    public static int channelNum = 0;

    public static synchronized int getChannelNum() {
        return channelNum;
    }

    public static synchronized void addChannelNum() {
        ChannelMap.channelNum++;
    }

    public static synchronized void removeChannelNum() {
        ChannelMap.channelNum--;
    }


    private static ConcurrentHashMap<String, Channel> channelHashMap = null;

//    public ChannelMap() {
//        channelHashMap = new ConcurrentHashMap<>();
//    }

//    public static ConcurrentHashMap<String, Channel> getChannelHashMap() {
//        return channelHashMap;
//    }

    public static Channel getChannelByName(String name) {
        return channelHashMap.get(name);
    }

    public static void addChannel(String name, Channel channel) {
        if (channelHashMap == null) {
            channelHashMap = new ConcurrentHashMap<>(100);
        }
        channelHashMap.put(name, channel);
        channelNum++;
    }

    public static int removeChannelByName(String name) {
        if (channelHashMap.containsKey(name)) {
            channelHashMap.remove(name);
            channelNum--;
            return 0;
        } else {
            return 1;
        }
    }

}
