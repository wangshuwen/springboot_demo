package com.zkxh.demo.netty.config;

import com.zkxh.demo.netty.codec.CustomDecoder;
import com.zkxh.demo.netty.codec.CustomEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName NettyServer
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/17 16:41
 * @Vserion v0.0.1
 */
@Component
public class NettyServer {

    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 端口号
     */
    @Value("${netty.port}")
    private int port;

    /**
     * 启动服务器方法
     */
    public void run() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            serverBootstrap.group(bossGroup, workerGroup);
            //设置socket工厂
            serverBootstrap.channel(NioServerSocketChannel.class);

            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {


                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipe = socketChannel.pipeline();
//                    pipe.addLast(new IdleStateHandler(10, 10, 20, TimeUnit.SECONDS));
                    pipe.addLast(new CustomDecoder(544, 14, 2, -22, 0, true));
                    pipe.addLast(new CustomEncoder());
                    pipe.addLast(new NettyServerHandler());
                }
            });
            //参数TCP设置
//            ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.ADVANCED);
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 2048);     // serverSocketChannel连接缓冲池大小
            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);   //socketChannel true维持连接活跃 默认2小时，无操作断开
            serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);    //socketChannel 关闭缓冲=关闭延迟发送
            ChannelFuture future = serverBootstrap.bind(this.port);


            log.info("服务端已启动，端口号 [" + this.port + "]");


            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
