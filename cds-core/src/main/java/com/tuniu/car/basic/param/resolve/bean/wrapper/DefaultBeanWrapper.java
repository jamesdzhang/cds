package com.tuniu.car.basic.param.resolve.bean.wrapper;

import com.tuniu.car.basic.vo.response.ResponseDto;
import com.tuniu.operation.platform.tsg.base.core.method.SuccessData;
import com.tuniu.car.basic.vo.response.ResponseVo;
import org.springframework.core.MethodParameter;


/**
 * 优先级最低的默认bean包装
 * 
 * @copyright(C)  James
 * @author James
 */
public class DefaultBeanWrapper extends AbstractBeanWrapper {

    public Object wrap(Object bean) {
        return new SuccessData(bean);
    }

    @Override
    public boolean supports(MethodParameter returnType) {
        return !ResponseDto.class.isAssignableFrom(returnType.getParameterType()) &&
                !ResponseVo.class.isAssignableFrom(returnType.getParameterType());
    }

}
