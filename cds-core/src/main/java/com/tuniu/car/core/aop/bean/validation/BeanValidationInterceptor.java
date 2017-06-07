package com.tuniu.car.core.aop.bean.validation;

import com.tuniu.car.basic.constants.ErrorMessages;
import com.tuniu.car.basic.exception.BusinessException;
import com.tuniu.car.basic.exception.ErrorCode;
import com.tuniu.car.basic.exception.ErrorCodeDefinition;
import com.tuniu.car.basic.vo.response.ResponseVo;
import org.apache.commons.lang.Validate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by zhangyaping on 2017/5/22.
 */

@Aspect
@Component
public class BeanValidationInterceptor {

    private static Logger logger = LoggerFactory.getLogger(BeanValidationInterceptor.class);


    @Pointcut(value = "@annotation(com.tuniu.car.basic.annotation.BeanValid)")
    public void apiParamPoint() {}

    @Around("apiParamPoint()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        try{
            //请开始验证
            validateParam(point.getArgs()[0]);
        }catch (Exception ex){
            return rejectParamError(ex.getMessage());
        }
        //没有注解的放掉
        return point.proceed();
    }

    private final static String JSONDATA_FLAG = "{}";

    private void validateParam(Object paramObj){
        if (paramObj != null && !JSONDATA_FLAG.equals(paramObj)) {
            if (paramObj instanceof List) {
                List<Object> paramObjectList = (List<Object>) paramObj;
                for (Object validObj : paramObjectList) {
                    validateObject(validObj);
                }
            } else {
                validateObject(paramObj);
            }
        }else{
            throw new IllegalArgumentException("参数格式有误");
        }
    }

    private ResponseVo rejectParamError(String errorMsg){
        ErrorCode code = ErrorCodeDefinition.ORD_BASEVALID_ERROR.dynamicMessage(
                ErrorMessages.COMMON_PARAMETER_ERROR, errorMsg);
        ResponseVo errorVo = new ResponseVo();
        errorVo.setMsg(code.getErrMessage());
        errorVo.setErrorCode(code.getErrCode());
        return errorVo;
    }


    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private void validateObject(Object validObject) {
        Validate.notNull(validObject);
        if(validator == null) {
            return;
        }
        Set<ConstraintViolation<Object>> violations = validator.validate(validObject);
        if (!violations.isEmpty()) {
            StringBuilder buffer = new StringBuilder();
            Iterator<ConstraintViolation<Object>> it = violations.iterator();
            while (it.hasNext()) {
                ConstraintViolation<Object> violation = it.next();
                buffer.append(violation.getPropertyPath()).append(":").append(violation.getMessage()).append(" ,");
            }
            buffer.deleteCharAt(buffer.length() - 1);

            // 异常封装，参数基础验证失败
            throw new BusinessException(ErrorCodeDefinition.ORD_BASEVALID_ERROR.dynamicMessage(
                    ErrorMessages.COMMON_PARAMETER_ERROR, buffer));
        }
    }

}
