package com.zkxh.demo;


import com.zkxh.demo.netty.config.NettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;


@MapperScan("com.zkxh.demo.dao")
@SpringBootApplication(scanBasePackages = "com.zkxh.demo", exclude = {HibernateJpaAutoConfiguration.class})
//@Configuration
//@EnableAdminServer
public class DemoApplication implements CommandLineRunner {


    @Resource
    NettyServer nettyServer;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DemoApplication.class);
        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        nettyServer.run();
    }

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation("D://temp");
        return factory.createMultipartConfig();
    }
}
