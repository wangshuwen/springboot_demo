package com.zkxh.demo;


import com.zkxh.demo.socket.server.SerSocket;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;



@MapperScan("com.zkxh.demo.dao")
@SpringBootApplication(scanBasePackages = "com.zkxh.demo", exclude = {HibernateJpaAutoConfiguration.class})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DemoApplication.class);
        //  springApplication.addListeners(new ApplicationContextListener());

        springApplication.run(args);
        new Thread(new SerSocket()).start();

    }


}
