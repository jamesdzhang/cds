package com.tuniu.car.basic.interceptor;

import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tuniu.car.basic.constants.Constants;
import com.tuniu.car.basic.interceptor.context.InvokeContext;
import com.tuniu.car.basic.support.BaseContext;
import com.tuniu.car.basic.support.InheritableBaseContext;
import com.tuniu.car.utils.IpUtil;
import com.tuniu.operation.platform.tsg.base.core.utils.JsonUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tuniu.operation.platform.tsg.base.core.annotation.Json;
import com.tuniu.operation.platform.tsg.base.core.method.impl.ResponseWrapper;
import com.tuniu.operation.platform.tsg.client.annotation.TSPServiceInfo;
/**
 * 请求处理拦截器
 * 目前用于Bean Validation
 * @author James
 * 
 */
public class FrameworkInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(FrameworkInterceptor.class);

    private static final String SALER_ID_KEY = "uid";

    private static final String SALER_NICKNAME_KEY = "nickname";

    private static final String cookieUidKey = "boss3TocUserId";

    private static final String cookieUNameKey = "boss3TocUserName";

    //服务器ip
    private static String serverIp;

    //服务器port
    @Value(value = "@{server.port}")
    private static String serverPort;

    //当前运行时间
    private static long serverStartTime;

    /**
     * 处理动作集
     */
    private List<FilterAction> actions;

    /**
     * 防重复的URI集合
     */
    private List<String> includes;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String sqlServiceSource = request.getRequestURL().toString();

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            setCurrentUserInfo(request, handlerMethod);
            Class<?> methodReturnType = handlerMethod.getMethod().getReturnType();
            TSPServiceInfo tspService = handlerMethod.getMethodAnnotation(TSPServiceInfo.class);
            if (tspService != null && StringUtils.isNotBlank(tspService.name())) {
                sqlServiceSource = sqlServiceSource.concat("/").concat(tspService.name());
            }

            initThreadContextParams(request, response, sqlServiceSource);

            if (methodReturnType != null && !methodReturnType.equals(String.class)) {
                InvokeContext context = new InvokeContext(request, response, handlerMethod);
                context.setActions(actions);
                context.setIncludes(includes);
                context.startInvoke();
                return context.getExecuteResult();
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        try {
            initServerInfo();
            response.addHeader(Constants.TN_SERVER_ADDR,serverIp);
            response.addHeader(Constants.TN_SERVER_PORT,serverPort);
            response.addHeader(Constants.TN_SERVER_RUN_TIME, getServerRunTime());
            response.addHeader(Constants.TN_SERVER_THREADCOUNT, getThreadCount());
            if (handler instanceof HandlerMethod) {
                // 调完controller之后，包含指定URI的请求，保存响应结果
                String seqNo = StringUtils.defaultIfEmpty(request.getHeader(Constants.TN_SEQNO),
                        BaseContext.getProperty(Constants.TN_SEQNO));
                String source = StringUtils.defaultIfEmpty(request.getHeader(Constants.TN_SOURCE),
                        BaseContext.getProperty(Constants.TN_SOURCE));
                if (StringUtils.isNotBlank(seqNo) && StringUtils.isNotBlank(source)) {
                    Class<?> methodReturnType = ((HandlerMethod) handler).getMethod().getReturnType();
                    if (methodReturnType != null && !methodReturnType.equals(String.class)) {
                        InvokeContext context = new InvokeContext(request, response, (HandlerMethod) handler);
                        context.setActions(actions);
                        context.setIncludes(includes);
                        if (response instanceof ResponseWrapper) {
                            saveResponseLog(request, (ResponseWrapper)response, seqNo, source);
                        } else {
                        }
                    }
                }
            }
        } finally {
            BaseContext.clearThreadContextInfo();
            // dsbu by guanhuajie
            InheritableBaseContext.clearThreadContextInfo();
        }
    }
    
	private void saveResponseLog(HttpServletRequest request, ResponseWrapper response, String seqNo, String source)
			throws Exception {
		byte[] bytes = response.getResponseData();
		String respString = new String(Base64.decodeBase64(bytes), Constants.CHARSET);
//		CommonInvokeLogUtil.saveResponseLog(seqNo, source, sysCode, respString, request.getRequestURL().toString());
	}

    public void setActions(List<FilterAction> actions) {
        this.actions = actions;
    }

    public void setIncludes(List<String> includes) {
        this.includes = includes;
    }

    // 获取当前人员信息,存入ThreaLocal中
    private void setCurrentUserInfo(HttpServletRequest request, HandlerMethod method) {
        boolean hasJsonAnno = false;
        MethodParameter[] parameters = method.getMethodParameters();
        for (MethodParameter parameter : parameters) {
            if (parameter.hasParameterAnnotation(Json.class)
                    || parameter.hasParameterAnnotation(Json.class)) {
                hasJsonAnno = true;
                break;
            }
        }
        Object salerId = null;
        Object salerName = null;
        salerId = getCookieParams(request, cookieUidKey);
        salerName = getCookieParams(request, cookieUNameKey);
        if (salerId != null) {
            BaseContext.setObjectProperty(Constants.CURRENT_UID_KEY, salerId);
        }
        if (salerName != null) {
            BaseContext.setObjectProperty(Constants.CURRENT_SALER_NAME_KEY, salerName);
        }
    }

    private String getCookieParams(HttpServletRequest request, String key) {
        String result = null;
        if (StringUtils.isNotBlank(key)) {
            Cookie[] cookies = request.getCookies();
            if (!ArrayUtils.isEmpty(cookies)) {
                for (Cookie cookie : cookies) {
                    if (key.equals(cookie.getName())) {
                        result = cookie.getValue();
                        break;
                    }
                }
            }
        }
        /*
         * if("null".equals(result)||"NULL".equals(result)){ result=null; }else
         */if (StringUtils.isNotBlank(result)) {
            try {
                result = URLDecoder.decode(result.replace("+", "%2B"), "UTF-8");
                if (!StringUtils.isNumeric(result) && Base64.isBase64(result)) {
                    try {
                        result = new String(Base64.decodeBase64(result), "UTF-8");
                    } catch (Exception e) {
                        logger.warn("ExceptionInfo:{}", JsonUtil.toString(e));
                    }
                }
            } catch (UnsupportedEncodingException e) {
                logger.warn("ExceptionInfo:{}", JsonUtil.toString(e));
            }
        }
        return result;
    }

    private void initThreadContextParams(HttpServletRequest request, HttpServletResponse response, String sqlSource) {

        String seqNo = request.getHeader(Constants.TN_SEQNO);
        String source = request.getHeader(Constants.TN_SOURCE);
        if (StringUtils.isBlank(seqNo)) {
            seqNo = String.valueOf(System.currentTimeMillis());
        }
        if (StringUtils.isBlank(source)) {
            source = "WEB_DEF";
        }
        BaseContext.setProperty(Constants.TN_SEQNO, seqNo);
        BaseContext.setProperty(Constants.TN_SOURCE, source);

        // sql频度统计使用
        BaseContext.setProperty(Constants.SQL_SERVICE_SOURCE, sqlSource);
        // sql频度统计使用
        InheritableBaseContext.setObjectProperty(Constants.SQL_SERVICE_SOURCE, sqlSource);

        response.setHeader(Constants.TN_SEQNO, seqNo);

    }

    private void initServerInfo() {
        try {
            if (serverIp == null) {
                serverIp = IpUtil.getServerIP();
            }
            if (serverPort == null) {
                serverPort = String.valueOf(serverPort);
            }
            if (serverStartTime == 0) {
                serverStartTime = ManagementFactory.getRuntimeMXBean().getStartTime();
            }
        } catch (Exception e) {
            logger.warn("初始化实例信息失败.",e);
        }
    }

    private String getServerRunTime(){
        try {
            return String.valueOf((System.currentTimeMillis() - serverStartTime)/60000);
        } catch (Exception e) {
            logger.warn("获取运行时间失败.",e);
        }
        return null;
    }

    private String getThreadCount(){
        try {
            return String.valueOf(ManagementFactory.getThreadMXBean().getThreadCount());
        } catch (Exception e) {
            logger.warn("获取线程数失败.",e);
        }
        return null;
    }

}
