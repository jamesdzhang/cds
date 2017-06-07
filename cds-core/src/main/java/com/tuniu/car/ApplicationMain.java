package com.tuniu.car;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by zhangyaping on 2017/4/18.
 * 禁用spring boot自己的jms自动配置
 */
@SpringBootApplication(exclude = JmsAutoConfiguration.class)
public class ApplicationMain {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(ApplicationMain.class);

    public static void main(String args[]){
        ConfigurableApplicationContext context = SpringApplication.run(CdsConfiguration.class, args);
        logger.info("CDS - ApplicationMain is started");
    }

}
