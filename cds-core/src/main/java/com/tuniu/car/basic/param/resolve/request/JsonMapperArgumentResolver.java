package com.tuniu.car.basic.param.resolve.request;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.tuniu.car.basic.annotation.FromRDP;
import com.tuniu.car.basic.exception.BusinessException;
import com.tuniu.car.basic.exception.ErrorCodeDefinition;
import com.tuniu.car.basic.param.resolve.ObjectMapperFactory;
import com.tuniu.operation.platform.tsg.base.core.annotation.Json;
import com.tuniu.operation.platform.tsg.base.core.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

/**
 * 
 * @copyright(C)  James
 * @author James
 */
public class JsonMapperArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger log = LoggerFactory.getLogger(JsonMapperArgumentResolver.class);

    private ObjectMapper objectMapper;
    private static final String PATH_DELIMITER = "/";

    public JsonMapperArgumentResolver() {
        objectMapper = ObjectMapperFactory.getDefaultObjectMapper();
    }

    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Json.class);
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        try {
            Json jsonAnn = parameter.getParameterAnnotation(Json.class);
            String path = jsonAnn.path();
            String allParam = getRequestParam(webRequest);
            if (allParam == null || allParam.length() == 0) {
                return null;
            }
            //RDP来源的参数处理，因为结构不同
            FromRDP fromRdpAnn = parameter.getParameterAnnotation(FromRDP.class);// fromRdpAnn=null
            if(fromRdpAnn!=null){
                //是从rdp来的数据，做个兼容
                final Map<String,Object> rawParam = JsonUtil.toBean(allParam,Map.class);
                String retailId = String.valueOf(rawParam.get("retailId"));
                Map<String,Object> data = JsonUtil.toBean(rawParam.get("data"),Map.class);
                data.put("retailId",retailId);
                //重写请求参数
                allParam = JsonUtil.toString(data);
            }
            JsonNode node = objectMapper.readTree(allParam);
            if (path == null || "".equals(path)) {
                path = parameter.getParameterName();
                if (node.has(path)) {
                    ObjectReader objectReader = objectMapper.reader(getReferenceType(parameter));
                    return objectReader.readValue(node.path(path));
                }
                    return objectMapper.readValue(allParam, getReferenceType(parameter));

            } else {
                String[] paths = path.split(PATH_DELIMITER);
                for (String p : paths) {
                    node = node.path(p);
                }
                if (node == null) {
                    return null;
                }
                ObjectReader objectReader = objectMapper.reader(getReferenceType(parameter));
                return objectReader.readValue(node);
            }
        } catch (Exception e) {
            log.error("can't generate param [" + parameter.getParameterName() + "] for url "
                    + webRequest.getNativeRequest(HttpServletRequest.class).getServletPath() + ", and source input is "
                    + getRequestParam(webRequest));
            //配合tsp的framework  因为FrameWorkFilter的异常处理只支持JsonParseException和RestErrorCodeException
            //否则会抛出RuntimeException 无法把封装给上有系统
            throw new BusinessException(ErrorCodeDefinition.PARAM_PARSE_ERROR.getErrorCode());
        }
    }

    /**
     * 获取反射的对象类型
     */
    private JavaType getReferenceType(MethodParameter parameter) {
        Type type = parameter.getGenericParameterType();
        return getReferenceType(type);
    }

    private JavaType getReferenceType(Type type) {
        if (type instanceof ParameterizedType) {
            Type[] genericTypes = ((ParameterizedType) type).getActualTypeArguments();
            Class<?> parameterType = (Class<?>) ((ParameterizedType) type).getRawType();
            if (Collection.class.isAssignableFrom(parameterType)) {
                if (genericTypes.length >= 1) {
                    return objectMapper.getTypeFactory().constructCollectionType(
                            (Class<? extends Collection>) parameterType, getReferenceType(genericTypes[0]));
                }

            } else if (Map.class.isAssignableFrom(parameterType)) {
                if (genericTypes.length >= 2) {
                    return objectMapper.getTypeFactory().constructMapType((Class<? extends Map>) parameterType,
                            getReferenceType(genericTypes[0]), getReferenceType(genericTypes[1]));
                } else if (genericTypes.length == 1) {
                    return objectMapper.getTypeFactory().constructMapType((Class<? extends Map>) parameterType,
                            getReferenceType(genericTypes[0]), getReferenceType(Object.class));
                } else {
                    return objectMapper.getTypeFactory().constructMapType((Class<? extends Map>) parameterType,
                            Object.class, Object.class);
                }

            }
            //其他交给Databind-specific annotations处理
            throw new UnsupportedOperationException("Unsuppored Reference To JavaType " + type);
        }
        return objectMapper.getTypeFactory().constructType(type);
    }

    /**
     * 获取HttpServletRequest参数体
     * 
     * @param webRequest
     * @return
     * @throws IOException
     */
    private String getRequestParam(NativeWebRequest webRequest) throws IOException {
        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        String method = httpServletRequest.getMethod();
        if (method.equals("GET") || method.equals("DELETE")) {
            return httpServletRequest.getQueryString();
        }
        StringBuilder buffer = new StringBuilder();
        String line;
        BufferedReader reader = httpServletRequest.getReader();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        /*if(buffer.toString().equals("")){
            line = String.valueOf(((Base64HttpServletRequestWrapper) ((ServletWebRequest) webRequest).getRequest()).
                    getRequest().getParameterMap().keySet().toArray()[0]);
            buffer.append(new String((new BASE64Decoder()).decodeBuffer(line)));
        }*/
        return buffer.toString();
    }

}
