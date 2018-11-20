package com.zkxh.demo.netty.config;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @ClassName NettyServerListener
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/17 16:43
 * @Vserion v0.0.1
 */

@WebListener
public class NettyServerListener implements ServletContextListener {

    /**
     * 注入NettyServer
     */
    @Autowired
    private NettyServer nettyServer;

    @Autowired
    private NettyRTCServer nettyRTCServer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Thread thread = new Thread(new NettyServerThread());
        // 启动netty服务
        thread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    /**
     * netty服务启动线程
     *
     * @author lifeng
     */
    private class NettyServerThread implements Runnable {

        @Override
        public void run() {

            nettyServer.run();
            nettyRTCServer.run();
        }
    }

}
