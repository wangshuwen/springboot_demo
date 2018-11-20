package com.zkxh.demo.netty.config;

import com.zkxh.demo.netty.request.ChannelMap;
import com.zkxh.demo.websocket.WebRTC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.websocket.Session;
import java.net.InetSocketAddress;

/**
 * @ClassName WebRTCHandler
 * @Description
 * @Auther lifeng
 * @DATE 2018/10/30 15:00
 * @Vserion v0.0.1
 */
@Sharable
@Component
public class WebRTCHandler extends ChannelInboundHandlerAdapter {

    private static WebRTCHandler webRTCHandler;
    @Resource
    private WebRTC webRTC;

    @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
    public void init() {
        webRTCHandler = this;
        webRTCHandler.webRTC = this.webRTC;
//        nettyServerHandler.client = this.client;
    }

    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println(msg);
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] req = new byte[byteBuf.readableBytes()];

        byteBuf.readBytes(req);
        for (byte b : req) {
            System.out.printf(" 0x%02x", b);
        }
        webRTCHandler.webRTC.sendMessage(req);
//        webRTC.onMessage(msg,session,1 );
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        int port = insocket.getPort();
        log.info("客户端[" + clientIP + ":" + port + "] 连接成功");
        log.info("客户端[" + clientIP + ":" + port + "] 加入session");
        System.out.println(clientIP);
        ChannelMap.addChannel(clientIP, ctx.channel());
        log.info("当前连接基站数量" + ChannelMap.getChannelNum());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        int port = insocket.getPort();
        log.info("客户端[" + clientIP + ":" + port + "] 已断开连接");
        log.info("客户端[" + clientIP + "] 被移出session");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        int port = insocket.getPort();
        log.info("读取客户端[" + clientIP + ":" + port + "] 数据完成");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        int port = insocket.getPort();
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                //TODO 读超时
                ctx.channel().close();
            }
            if (event.state() == IdleState.WRITER_IDLE) {
                //TODO 写超时
                ctx.channel().close();
            }
            if (event.state() == IdleState.ALL_IDLE) {
                //清除超时会话
                ChannelFuture writeAndFlush = ctx.writeAndFlush("client will be remove");
                writeAndFlush.addListener((ChannelFutureListener) future -> {
                    //TODO 通知web端显示并存储数据库
                    //TODO 客户端掉线
                    ctx.channel().close();
                });
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        log.error("客户端[" + clientIP + "] 出现异常" + cause.getLocalizedMessage());
        cause.printStackTrace();
    }

}
