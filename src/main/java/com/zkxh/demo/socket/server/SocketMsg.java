package com.zkxh.demo.socket.server;

/**
 * @ClassName SocketMsg
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/4 13:20
 * @Vserion v0.0.1
 */

public enum SocketMsg {

    //===============数据头===============
    MSG_HEADER_FREAME_HEAD(0x8866, "帧头"),
    MSG_HEADER_COMMAND_ID_SEARCH(0x1100, "查询"),
    MSG_HEADER_COMMAND_ID_CONTROL(0x1200, "控制"),
    MSG_HEADER_COMMAND_ID_NULL(0x1300, "空"),
    MSG_HEADER_COMMAND_ID_REQUEST(0x1500, "采集数据上报（设备到后台）"),
    MSG_HEADER_COMMAND_ID_RESPONSE(0x1600, "应答"),
    MSG_HEADER_COMMAND_ID_LOGIN(0x1b00, "web服务器登录命令"),
    MSG_HEADER_COMMAND_ID_HEARTBEAT(0x1c00, "心跳数据"),


    //===============数据体===============
    MSG_BODY_RESULT_SUCCESS(0x55, "消息体成功标志"),
    MSG_BODY_RESULT_ERROR(0x22, "消息体失败标志"),
    MSG_BODY_RESULT_NO_MEAN(-1, "无意义标志"),
    MSG_BODY_NODE_NAME_HANDWARE_VERSION(0x0110, "硬件版本号"),
    MSG_BODY_NODE_NAME_SOFTWARE_VERSION(0x0210, "软件版本号"),
    MSG_BODY_NODE_NAME_SELFCHECK_RESULT(0x0310, "自检结果"),
    MSG_BODY_NODE_NAME_SENSOR_DATA(0x0120, "传感器数据"),
    MSG_BODY_NODE_NAME_LOCATOR_DATA(0x0220, "定位数据"),
    MSG_BODY_NODE_NAME_VOICE_DATA(0x0320, "语音数据"),
    MSG_BODY_NODE_NAME_HEARTBEAT(0x0130, "心跳"),


    //===============硬件故障代码===============
    MSG_BODY_HANDWARE_ERROR_WIFI(0x0100, "WIFI故障"),
    MSG_BODY_HANDWARE_ERROR_VOICE(0x0200, "语音故障"),
    MSG_BODY_HANDWARE_ERROR_CO(0x0300, "一氧化碳传感器故障"),
    MSG_BODY_HANDWARE_ERROR_CO2(0x0400, "二氧化碳传感器故障"),
    MSG_BODY_HANDWARE_ERROR_O2(0x0500, "氧气传感器故障"),
    MSG_BODY_HANDWARE_ERROR_CH4(0x0600, "甲烷传感器故障"),
    MSG_BODY_HANDWARE_ERROR_HUMITURE(0x0700, "温湿度传感器故障"),

    ;


    private int code;
    private String msg;

    SocketMsg() {
    }

    SocketMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
