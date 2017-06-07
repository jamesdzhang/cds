package com.tuniu.car.basic.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法参数验证标记注解
 * @author James
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BeanValid {
    /**
     * 是否必须校验 此属性可与NotNullParameter搭配使用,
     * 当存在个别接口的入参不是必需的且入参对应的类上加了NotNullParameter注解时可通过此属性跳过非必填限制
     * @return
     */
    boolean required() default true;

}
