package com.zkxh.demo.netty.data;

import io.netty.buffer.ByteBuf;

/**
 * @ClassName VoiceInfo
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/26 19:48
 * @Vserion v0.0.1
 */

public class VoiceInfo {

    private Integer count;

    private ByteBuf data;


    public VoiceInfo(Integer count, ByteBuf data) {
        this.count = count;
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ByteBuf getData() {
        return data;
    }

    public void setData(ByteBuf data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "VoiceInfo{" +
                "count=" + count +
                ", data=" + data +
                '}';
    }
}
