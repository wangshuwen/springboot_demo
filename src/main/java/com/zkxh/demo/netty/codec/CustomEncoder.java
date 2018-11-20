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

public class CustomEncoder extends MessageToByteEncoder<byte[]> {
//
//    @Override
//    protected void encode(ChannelHandlerContext channelHandlerContext, ResponseData resp, ByteBuf byteBuf) throws Exception {
//
//        if (resp == null) {
//            throw new Exception("回传消息为空");
//        }
//
//        ResponsePkg response = new ResponsePkg();
//
////        System.out.println("aa" + resp.getCustomMsg().getBody().length);
////        System.out.println("aa" + resp.getCustomMsg().getNdName());
////        System.out.println("aa" + resp.getCustomMsg().getNodeCount());
////        System.out.println(resp.getCustomMsg().toString());
////        byteBuf.writeBytes(response.dataResponse(resp.getCustomMsg()));
////        byteBuf.writeBytes(response.dataResponseVoice(resp.getCustomMsg()));
//    }


    @Override
    protected void encode(ChannelHandlerContext ctx, byte[] msg, ByteBuf out) throws Exception {
        out.writeBytes(msg);
    }
}
