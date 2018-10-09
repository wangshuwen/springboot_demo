package com.zkxh.demo.netty.utils;

/**
 * @ClassName ConstantValue
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/20 15:16
 * @Vserion v0.0.1
 */

public class ConstantValue {
    public static final String basePath = "d://voice/file/";

    //===============数据头===============
    public static final int MSG_HEADER_FREAME_HEAD = 0x6688;//, "帧头"),
    public static final int MSG_HEADER_COMMAND_ID_SEARCH = 0x0011;    //  , "查询"),
    public static final int MSG_HEADER_COMMAND_ID_CONTROL = 0x0012;  //  , "控制"),
    public static final int MSG_HEADER_COMMAND_ID_NULL = 0x0013;  //  , "空"),
    public static final int MSG_HEADER_COMMAND_ID_REQUEST = 0x0015;   //  , "采集数据上报（设备到后台）"),
    public static final int MSG_HEADER_COMMAND_ID_RESPONSE = 0x0016;   //  , "应答"),
    public static final int MSG_HEADER_COMMAND_ID_LOGIN = 0x001b;    //  , "web服务器登录命令"),
    public static final int MSG_HEADER_COMMAND_ID_HEARTBEAT = 0x001c; //  , "心跳数据"),


    //===============数据体===============
    public static final int MSG_BODY_RESULT_SUCCESS = 0x55;  //  "消息体成功标志"),
    public static final int MSG_BODY_RESULT_ERROR = 0x22;   //  "消息体失败标志"),
    public static final int MSG_BODY_RESULT_NO_MEAN = -1; //  "无  意义标志"),
    public static final int MSG_BODY_NODE_NAME_HANDWARE_VERSION = 0x1001;   //  , "硬件版本号"),
    public static final int MSG_BODY_NODE_NAME_SOFTWARE_VERSION = 0x1002; //  , "软件版本号"),
    public static final int MSG_BODY_NODE_NAME_SELFCHECK_RESULT = 0x1003;   //  , "自检结果"),
    public static final int MSG_BODY_NODE_NAME_SENSOR_DATA = 0x2001;  //  , "传感器数据"),
    public static final int MSG_BODY_NODE_NAME_LOCATOR_DATA = 0x2002;   //  , "定位数据"),
    public static final int MSG_BODY_NODE_NAME_VOICE_DATA = 0x2003;   //  , "语音数据"),
    public static final int MSG_BODY_NODE_NAME_HEARTBEAT = 0x3001;//  , "心跳"),


    //===============硬件故障代码===============
    public static final int MSG_BODY_HANDWARE_ERROR_WIFI = 0x0100; //  , "WIFI故障"),
    public static final int MSG_BODY_HANDWARE_ERROR_VOICE = 0x0200;   //  , "语音故障"),
    public static final int MSG_BODY_HANDWARE_ERROR_CO = 0x0300;  //  , "一氧化碳传感器故障"),
    public static final int MSG_BODY_HANDWARE_ERROR_CO2 = 0x0400;   //  , "二氧化碳传感器故障"),
    public static final int MSG_BODY_HANDWARE_ERROR_O2 = 0x0500;  //  , "氧气传感器故障"),
    public static final int MSG_BODY_HANDWARE_ERROR_CH4 = 0x0600;  //  , "甲烷传感器故障"),
    public static final int MSG_BODY_HANDWARE_ERROR_HUMITURE = 0x0700;  //  , "温湿度传感器故障"),

}
