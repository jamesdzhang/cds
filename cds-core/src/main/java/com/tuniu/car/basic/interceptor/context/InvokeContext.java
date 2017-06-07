package com.tuniu.car.basic.interceptor.context;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tuniu.car.basic.constants.Constants;
import com.tuniu.car.basic.constants.ErrorMessages;
import com.tuniu.car.basic.exception.BusinessException;
import com.tuniu.car.basic.exception.ErrorCode;
import com.tuniu.car.basic.exception.ErrorCodeDefinition;
import com.tuniu.car.basic.exception.SystemException;
import com.tuniu.car.basic.interceptor.FilterAction;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tuniu.operation.platform.tsg.base.core.utils.JsonUtil;
import com.tuniu.car.basic.vo.response.ResponseVo;

/**
 * 调用上下文
 * 
 * @author James
 * 
 */
public class InvokeContext {
    private static Logger logger = LoggerFactory.getLogger(InvokeContext.class);

    /** 请求 */
    private HttpServletRequest request;

    /** 响应 */
    private HttpServletResponse response;

    /** 方法句柄 */
    private HandlerMethod handlerMethod;

    /** MV */
    private ModelAndView modelAndView;

    /** 任务执行下标 */
    private int index;

    /** 任务链 */
    private List<FilterAction> actions;

    /** 请求参数 */
    private String JsonParameter;

    /** 任务执行结果 */
    private List<Boolean> executeResult = new ArrayList<Boolean>();

    /**
     * 防重复的URI集合
     */
    private List<String> includes;

    /**
     * 调用记录
     */
    private boolean existInvokeRequestLog;

    /**
     * 响应记录
     */
    private boolean existInvokeResponseLog;

    public InvokeContext(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) {
        super();
        this.request = request;
        this.response = response;
        this.handlerMethod = handlerMethod;
    }

    public InvokeContext(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod,
            ModelAndView modelAndView) {
        super();
        this.request = request;
        this.response = response;
        this.handlerMethod = handlerMethod;
        this.modelAndView = modelAndView;
    }

    public void setActions(List<FilterAction> actions) {
        this.actions = actions;
    }

    public ModelAndView getModelAndView() {
        return modelAndView;
    }

    public void setModelAndView(ModelAndView modelAndView) {
        this.modelAndView = modelAndView;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void setHandlerMethod(HandlerMethod handlerMethod) {
        this.handlerMethod = handlerMethod;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public HandlerMethod getHandlerMethod() {
        return handlerMethod;
    }

    public String getJsonParameter() {
        return JsonParameter;
    }

    public List<String> getIncludes() {
        return includes;
    }

    public void setIncludes(List<String> includes) {
        this.includes = includes;
    }

    public void setJsonParameter(String jsonParameter) {
        JsonParameter = jsonParameter;
    }

    public boolean isExistInvokeRequestLog() {
        return existInvokeRequestLog;
    }

    public void setExistInvokeRequestLog(boolean existInvokeRequestLog) {
        this.existInvokeRequestLog = existInvokeRequestLog;
    }

    public boolean isExistInvokeResponseLog() {
        return existInvokeResponseLog;
    }

    public void setExistInvokeResponseLog(boolean existInvokeResponseLog) {
        this.existInvokeResponseLog = existInvokeResponseLog;
    }

    /**
     * 指定UR防重复
     */
    public boolean includeRequestURI() {
        String requestPath = StringUtils.substringAfter(getRequest().getRequestURI(), getRequest().getContextPath());
        if (getIncludes() != null) {
            return getIncludes().contains(requestPath);
        }
        return true;
    }

    /**
     * 中断链，处理完成
     */
    public void complete() {
        executeResult.add(Boolean.FALSE);
    }

    /**
     * 重置链
     */
    public void reset() {
        this.index = 0;
    }

    /**
     * 启动链
     */
    public void startInvoke() {
        reset();
        invokeNext();
    }

    /**
     * 获取各个链的执行结果，不能有错误
     */
    public synchronized boolean getExecuteResult() {
        for (boolean bool : executeResult) {
            if (!bool) {
                return bool;
            }
        }
        return true;
    }

    /**
     * 发送响应
     */
    public void sendResponseData(ResponseVo responseVo, String status, int responseStatus) throws SystemException {
        this.sendResponseData(JsonUtil.toString(responseVo), status, responseStatus);
    }

    /**
     * 发送响应
     * 入参： jsonData 
     *            返回格式：{"success":true,"msg":"addMobileOrdersuccess","errorCode"
     *            :0,"data":{"orderId":2997839}}
     */
    public void sendResponseData(String jsonData, String status, int responseStatus) throws SystemException {
        try {
            PrintWriter writer = response.getWriter();
            writer.write(Base64.encodeBase64String(jsonData.getBytes(Constants.CHARSET)));
            response.setHeader(Constants.TN_STATUS, status);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            response.setStatus(responseStatus);
            response.flushBuffer();
        } catch (IOException e) {
        	//包装pga系统异常
			throw new SystemException(
					ErrorCodeDefinition.CONTEXT_SYS_ERROR.dynamicMessage(
							ErrorMessages.COMMON_PLATFORM_ERROR,
							new Object[] { e.getMessage() }), e);
        }
    }

    /**
     * 构建响应返回bean
     */
    public ResponseVo buildResponseVo(boolean success, String msg, int errorCode, String data) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setMsg(msg);
        responseVo.setSuccess(success);
        responseVo.setErrorCode(errorCode);
        responseVo.setData(data);
        return responseVo;
    }

    /**
     * 链传递 <br>
     * 异常处理： 任何一个任务执行产生异常，发送异常信息给调用端
     */
    public synchronized void invokeNext() {
        if (actions != null && !actions.isEmpty() && getExecuteResult()) {
            try {
                if (index <= actions.size() - 1) {
                    FilterAction action = actions.get(index);
                    index++;
                    action.invoke(this);
                    executeResult.add(Boolean.TRUE);
                }
            } catch (Exception e) {
            	logger.warn("Failed to invokeNext.", e);
                executeResult.add(Boolean.FALSE);
                
                //默认为系统异常
				ErrorCode errorCode = ErrorCodeDefinition.OTHER_SYSTEM_ERROR
						.getErrorCode();
                
                if (e instanceof BusinessException) {//返回的业务异常
                    errorCode = ((BusinessException) e).getErrorCode();
                }
                if (e instanceof SystemException) {//返回的系统异常
                	errorCode = ((SystemException) e).getErrorCode();
                }
                else
                {
                	//框架异常
                	errorCode = ErrorCodeDefinition.INTF_ANALYZE_ERROR
    						.setMessage(e.getMessage());
                }
                
                ResponseVo responseVo = buildResponseVo(Boolean.FALSE, errorCode.getErrMessage(),
                        errorCode.getErrCode(), null);
                
                try {
                    sendResponseData(responseVo, Constants.TN_STATUS_ERR, HttpServletResponse.SC_OK);
                } catch (SystemException e1) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            }
        }
    }
}
