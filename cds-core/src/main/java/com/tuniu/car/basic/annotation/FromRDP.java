package com.tuniu.car.basic.annotation;

import java.lang.annotation.*;

/**
 * RDP的调用,参数做一层解析用
 * 他们的数据格式是{"timestamp":"2017-05-19 14:25:36","retailId":"101","sign":"7DF2947C6A6242E106F6864769C6750D","data":{"xxx"}}}
 * 而我们只需要data的数据
 * @author James
 * 
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FromRDP {
    /**
     * 标记是来自RDP的调用
     * @return
     */
    boolean required() default true;

}
