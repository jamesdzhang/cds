package com.tuniu.car.basic.interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.tuniu.car.basic.annotation.BeanValid;
import com.tuniu.car.basic.annotation.NotNullParameter;
import com.tuniu.car.basic.constants.ErrorMessages;
import com.tuniu.car.basic.exception.BusinessException;
import com.tuniu.car.basic.exception.ErrorCodeDefinition;
import com.tuniu.car.basic.interceptor.context.InvokeContext;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.PathVariable;

import com.tuniu.operation.platform.tsg.base.core.annotation.Json;

/**
 * 属性解析及属性验证任务
 * 这里引用了tob的bean验证
 * @author James
 * @update by James
 */
public class PropertyAnalyticsAction implements FilterAction {

    private static final Logger logger  = LoggerFactory.getLogger(Logger.class);
    
    private static final String PATH_DELIMITER = "/";

    /**
     * Jackson的对象映射器
     */
    private ObjectMapper objectMapper;
    {
        // 初始化
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private final static String JSONDATA_FLAG = "{}";
    @Override
    public void invoke(InvokeContext invokeContext) throws Exception {
        // 1.解析请求
        String jsonData = getJsonParameter(invokeContext.getRequest());
        // 2.参数转化及根据JSR303规范进行属性验证
        BeanValid beanValid = invokeContext.getHandlerMethod().getMethodAnnotation(BeanValid.class);
        try {
            if (StringUtils.isNotBlank(jsonData) && !JSONDATA_FLAG.equals(jsonData)) {
                invokeContext.setJsonParameter(jsonData);
                if (beanValid != null) {
                    MethodParameter[] methodParameters = invokeContext.getHandlerMethod().getMethodParameters();
                    for (MethodParameter methodParameter : methodParameters) {
                        if(!methodParameter.hasParameterAnnotation(Json.class)){
                            continue;
                        }
                        // 过滤掉不需要校验的参数
                        if (filterParameter(methodParameter)) {
                            continue;
                        }
                        Object validObject = resolveArgument(jsonData, invokeContext.getRequest(), methodParameter);
                        if (validObject instanceof List) {
                            @SuppressWarnings("unchecked")
                            List<Object> paramObjectList = (List<Object>) validObject;
                            for (Object validObj : paramObjectList) {
                                validateObject(validObj);
                            }
                        } else {
                            validateObject(validObject);
                        }
                    }
                }
            } else {
                NotNullParameter notNullAnno = null;
                MethodParameter[] methodParameters = invokeContext.getHandlerMethod().getMethodParameters();
                boolean forceRequired = true;
                for (MethodParameter methodParameter : methodParameters) {
                    notNullAnno = methodParameter.getParameterType().getAnnotation(NotNullParameter.class);
                    if (notNullAnno != null) {
                        forceRequired = beanValid.required();
                        if (forceRequired) {
                            // 异常封装，参数基础验证失败
                            throw new BusinessException(ErrorCodeDefinition.ORD_BASEVALID_ERROR.dynamicMessage(
                                    ErrorMessages.COMMON_PARAMETER_ERROR, notNullAnno.message()));
                        }
                    }
                }
            }
        } catch (Exception e) {
            HttpServletRequest request = invokeContext.getRequest();
            String requestUri = request.getRequestURI();
            throw new Exception(e.getMessage());
        }
        invokeContext.invokeNext();
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

    /**
     * 参数解析
     * 
     * @param jsonParam json格式的参数
     * @param request 请求
     * @param parameter spring方法参数描述
     * @return
     */
    private Object resolveArgument(String jsonParam, HttpServletRequest request, MethodParameter parameter)
            throws IOException {
        Json jsonAnn = parameter.getParameterAnnotation(Json.class);
        String path = jsonAnn.path();
        JsonNode node = objectMapper.readTree(jsonParam);
        if (StringUtils.isBlank(path)) {
            path = parameter.getParameterName();
            if (node.has(path)) {
                return objectMapper.readValue(node.path(path), getReferenceType(parameter, jsonAnn));
            }
            return objectMapper.readValue(jsonParam, getReferenceType(parameter, jsonAnn));
        } else {
            String[] paths = path.split(PATH_DELIMITER);
            for (String p : paths) {
                node = node.path(p);
            }
            if (node == null) {
                return null;
            }
            return objectMapper.readValue(node, getReferenceType(parameter, jsonAnn));
        }
    }

    /**
     * 获取反射的对象类型
     * 
     * @param parameter spring方法参数描述
     * @param annt json注解
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private JavaType getReferenceType(MethodParameter parameter, Json annt) {
        Class<?>[] types = annt.types();
        if (types.length == 1 && types[0].equals(Object.class)) {
            return objectMapper.getTypeFactory().constructType(parameter.getParameterType());
        }
        if (Collection.class.isAssignableFrom(parameter.getParameterType())) {
            return objectMapper.getTypeFactory().constructCollectionType(
                    (Class<? extends Collection>) parameter.getParameterType(), types[0]);
        } else if (Map.class.isAssignableFrom(parameter.getParameterType())) {
            if (types.length >= 2)
                return objectMapper.getTypeFactory().constructMapType(
                        (Class<? extends Map>) parameter.getParameterType(), types[0], types[1]);
            else
                return objectMapper.getTypeFactory().constructMapType(
                        (Class<? extends Map>) parameter.getParameterType(), types[0], Object.class);
        }

        StringBuilder buffer = new StringBuilder();
        buffer.append("Unsuppored Reference To JavaType : ").append(parameter.getParameterType().getName()).append("<");
        int i = 0;
        for (Class type : types) {
            if (i++ > 0)
                buffer.append(",");
            buffer.append(type.getSimpleName());
        }
        buffer.append(">");
        throw new UnsupportedOperationException(buffer.toString());
    }

    /**
     * 获取HttpServletRequest参数体
     * 
     * @param request
     * @return
     * @throws IOException
     */
    private String getJsonParameter(HttpServletRequest request) throws IOException {
        String method = request.getMethod();
        if (method.equals("GET") || method.equals("DELETE")) {
            return request.getQueryString();
        }
        StringBuilder buffer = new StringBuilder();
        String line;
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    /**
     * 参数校验过滤器
     * <p>
     * 主要整理不需要进行校验的方法参数
     * @param methodParameter
     * @return
     */
    private boolean filterParameter(MethodParameter methodParameter) {
        boolean filtered = false;
        if (null != methodParameter && (null == methodParameter.getParameterAnnotation(Json.class))
                && (null != methodParameter.getParameterAnnotation(PathVariable.class))) {
            filtered = true;
        }
        return filtered;
    }
}
