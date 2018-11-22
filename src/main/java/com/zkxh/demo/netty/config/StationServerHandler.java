package com.zkxh.demo.netty.config;

import com.zkxh.demo.common.da.kafka.KafkaSender;
import com.zkxh.demo.netty.station.StationChannelMap;
import com.zkxh.demo.service.gas.UpLoadService;
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
import java.net.InetSocketAddress;


/**
 * @ClassName StationServerHandler    基站 数据   业务处理类
 * @Description
 * @Auther lifeng
 * @DATE 2018/11/21 17:31
 * @Vserion v0.0.1
 */
@Sharable
@Component
public class StationServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(getClass());

    private static StationServerHandler stationServerHandler;
    //注入 上传数据服务
    @Resource
    private UpLoadService upLoadService;


//    @Resource
//    private Client client;

    @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
    public void init() {
        stationServerHandler = this;
        stationServerHandler.upLoadService = this.upLoadService;
    }

    //注入Kafka
    @Resource
    KafkaSender kafkaSender;


    /**
     * @param [ctx, msg]
     * @return void
     * @description 数据处理方法  执行主要业务
     * @date 17:33 2018/11/21
     * @auther lifeng
     **/
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    /**
     * @param [ctx]
     * @return void
     * @description 建立连接 执行的方法
     * @date 17:35 2018/11/21
     * @auther lifeng
     **/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        int port = insocket.getPort();
        log.info("基站[" + clientIP + ":" + port + "] 连接成功");
        System.out.println(clientIP);
        StationChannelMap.addChannel(clientIP, ctx.channel());
        log.info("基站[" + clientIP + ":" + port + "] 加入session");
        log.info("当前连接基站数量" + StationChannelMap.getChannelNum());
    }

    /**
     * @param [ctx]
     * @return void
     * @description 断开连接执行的方法
     * @date 17:35 2018/11/21
     * @auther lifeng
     **/
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        int port = insocket.getPort();
        log.info("基站[" + clientIP + ":" + port + "] 已断开连接");
        StationChannelMap.removeChannelByName(clientIP);
        log.info("基站[" + clientIP + "] 被移出session");
    }

    /**
     * @param [ctx]
     * @return void
     * @description 数据读取完成执行方法
     * @date 17:35 2018/11/21
     * @auther lifeng
     **/
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        int port = insocket.getPort();
        log.info("读取基站[" + clientIP + ":" + port + "] 数据完成");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("移除客户端");
    }

    /**
     * @param [ctx, evt]
     * @return void
     * @description 心跳处理
     * @date 17:36 2018/11/21
     * @auther lifeng
     **/
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
                StationChannelMap.removeChannelByName(clientIP);
            }
            if (event.state() == IdleState.WRITER_IDLE) {
                //TODO 写超时
                ctx.channel().close();
                StationChannelMap.removeChannelByName(clientIP);
            }
            if (event.state() == IdleState.ALL_IDLE) {
                //清除超时会话
                ChannelFuture writeAndFlush = ctx.writeAndFlush("client will be remove");
                writeAndFlush.addListener((ChannelFutureListener) future -> {
                    //TODO 通知web端显示并存储数据库
                    //TODO 基站掉线
                    ctx.channel().close();
                    StationChannelMap.removeChannelByName(clientIP);
                });
            }
        } else {
            super.userEventTriggered(ctx, evt);
            StationChannelMap.removeChannelByName(clientIP);
        }
    }

    /**
     * @param [ctx, cause]
     * @return void
     * @description 异常处理方法
     * @date 17:37 2018/11/21
     * @auther lifeng
     **/
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        log.error("基站[" + clientIP + "] 出现异常" + cause.getLocalizedMessage());
        StationChannelMap.removeChannelByName(clientIP);
        cause.printStackTrace();
    }


}
