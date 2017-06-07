package com.tuniu.car.basic.filter;

import com.tuniu.operation.platform.tsg.base.filter.Base64DecodingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

/**
 * Created by zhangyaping on 2017/4/19.
 */
@Component
@WebListener("/**")
public class Base64Filter extends Base64DecodingFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        super.doFilter(request, response, chain);
    }
}
