package com.zkxh.demo;


import com.zkxh.demo.netty.config.NettyRTCServer;
import com.zkxh.demo.netty.config.NettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import javax.annotation.Resource;


@MapperScan("com.zkxh.demo.dao")
@SpringBootApplication(scanBasePackages = "com.zkxh.demo", exclude = {HibernateJpaAutoConfiguration.class})
public class DemoApplication implements CommandLineRunner {


    @Resource
    NettyServer nettyServer;
    @Resource
    private NettyRTCServer nettyRTCServer;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DemoApplication.class);
        //  springApplication.addListeners(new ApplicationContextListener());
        springApplication.run(args);

        // new Thread(new SerSocket()).start();

    }

    @Override
    public void run(String... args) throws Exception {
        //1 nettyServer.run();
        nettyRTCServer.run();
    }
}
