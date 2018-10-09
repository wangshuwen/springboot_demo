package com.zkxh.demo.netty.codec;

import com.zkxh.demo.netty.data.response.ResponseData;
import com.zkxh.demo.netty.handle.ResponsePkg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


/**
 * @ClassName CustomEncoder
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/28 15:03
 * @Vserion v0.0.1
 */

public class CustomEncoder extends MessageToByteEncoder<ResponseData> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ResponseData resp, ByteBuf byteBuf) throws Exception {

        if (resp == null) {
            throw new Exception("回传消息为空");
        }

        ResponsePkg response = new ResponsePkg();

        byteBuf.writeBytes(response.dataResponse(resp.getCustomMsg(), resp.getCode()));
    }
}