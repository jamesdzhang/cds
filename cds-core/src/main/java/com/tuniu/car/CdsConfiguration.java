package com.tuniu.car;

import com.tuniu.operation.platform.tsg.base.filter.FrameWorkFilter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.HiddenHttpMethodFilter;


/**
 * Created by zhangyaping on 2017/3/14.
 */
@SpringBootConfiguration
@EnableScheduling
@ComponentScan("com.tuniu.car.*")
@ImportResource(locations = {"classpath*:application-start.xml"/*,"classpath*:config/log/logback_${envName}.xml"*/})
//@PropertySource("config/resource/common_${envName}.properties")
public class CdsConfiguration extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationMain.class);
    }
    /**
     * Disable spring boot one of the default filters,
     * because it will handle the request input stream before ours.
     * He is out!
     * @param filter
     * @return
     */
    @Bean
    public FilterRegistrationBean registration(HiddenHttpMethodFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }

    /**
     * register the tsp filter
     * @return
     */
    @Bean
    public FilterRegistrationBean tspFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new FrameWorkFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("applicationName", "CAR.CDS");
        registration.setName("tspFrameWorkFilter");
        return registration;
    }


}
