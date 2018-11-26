package com.zkxh.demo.netty.codec;

import com.zkxh.demo.netty.data.response.ResponseData;
import com.zkxh.demo.netty.handle.ResponsePkg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
/**
 * @author wangshuwen
 * @Description: 基站编码器
 * @Date 2018/11/23/11:03
 */

public class StationEncoder extends MessageToByteEncoder<ResponseData>{
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ResponseData responseData, ByteBuf byteBuf) throws Exception {
        if (responseData == null) {
            throw new Exception("回传消息为空");
        }
        ResponsePkg response = new ResponsePkg();
        byteBuf.writeBytes(response.dataResponse(responseData.getCustomMsg()));

    }
}
