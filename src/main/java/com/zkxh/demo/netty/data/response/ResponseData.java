package com.zkxh.demo.netty.data.response;


import com.zkxh.demo.netty.data.request.RequestData;

import java.io.Serializable;

/**
 * @ClassName Resp
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/28 15:24
 * @Vserion v0.0.1
 */

public class ResponseData implements Serializable {

    private RequestData customMsg; //请求内容

    private byte code;  //状态


    public RequestData getCustomMsg() {
        return customMsg;
    }

    public void setCustomMsg(RequestData customMsg) {
        this.customMsg = customMsg;
    }

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "customMsg=" + customMsg +
                ", code=" + code +
                '}';
    }
}
