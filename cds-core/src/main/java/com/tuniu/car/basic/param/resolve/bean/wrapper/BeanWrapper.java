package com.tuniu.car.basic.param.resolve.bean.wrapper;

import org.springframework.core.MethodParameter;

/**
 * 对象包装器
 * 
 * @copyright(C)  James
 * @author James
 */
public interface BeanWrapper {

    /**
     * 支持性判断
     * 
     * @param returnType
     * @return
     */
    boolean supportsType(MethodParameter returnType);

    /**
     * 对象包装
     * 
     * @param bean
     * @return
     */
    Object wrap(Object bean);
}
