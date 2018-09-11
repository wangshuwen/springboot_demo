package com.zkxh.demo.socket.server;

/**
 * @ClassName SocketEnum
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/3 14:50
 * @Vserion v0.0.1
 */

public enum SocketEnum {
    ERR_NO_ERROR(0x01, "无错误！"),
    ERR_RECV_PKG(0x01, "错误信息！"),
    ;

    private int code;
    private String msg;

    SocketEnum(Integer code) {
        this.code = code;
    }

    SocketEnum(String msg) {
        this.msg = msg;
    }

    SocketEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
