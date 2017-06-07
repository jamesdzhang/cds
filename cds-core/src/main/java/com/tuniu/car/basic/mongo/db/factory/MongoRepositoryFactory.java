/**
 * Copyright (C) 2006-2014 Tuniu All rights reserved
 * Author：James
 * Date：2014年8月20日
 * Description: 
 */

package com.tuniu.car.basic.mongo.db.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Description :
 * 
 */
public class MongoRepositoryFactory {

    private static ApplicationContext ctx = new ClassPathXmlApplicationContext(
            "classpath:config/spring/spring-mongo.xml");
    
    public static MongoRepository<?, ?> getInstance(String method) {
        if (ctx == null) {
            ctx = new ClassPathXmlApplicationContext("classpath:config/spring/spring-mongo.xml");
        }
        return (MongoRepository<?, ?>) ctx.getBean(method);
    }
}
