package com.tuniu.car.basic.param.resolve.bean.wrapper;

import com.tuniu.car.basic.vo.response.ResponseDto;
import org.springframework.core.MethodParameter;


/**
 * 
 * @copyright(C)  James
 * @author James
 */
public abstract class AbstractBeanWrapper implements BeanWrapper {

    public boolean supportsType(MethodParameter returnType) {
        if (ResponseDto.class.isAssignableFrom(returnType.getParameterType())) {
            return false;
        }
        return supports(returnType);
    }

    public abstract boolean supports(MethodParameter returnType);

}
