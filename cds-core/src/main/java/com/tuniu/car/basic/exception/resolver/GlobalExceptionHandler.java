package com.tuniu.car.basic.exception.resolver;

import com.tuniu.car.basic.exception.BusinessException;
import com.tuniu.car.basic.exception.ErrorCodeDefinition;
import com.tuniu.car.basic.param.resolve.request.JsonMapperArgumentResolver;
import com.tuniu.operation.platform.tsg.base.core.utils.JsonUtil;
import com.tuniu.car.basic.vo.response.ResponseVo;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(JsonMapperArgumentResolver.class);
    private final static String LOG_PRE_FIX="【有未定义的异常】";

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public void globalExHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        ResponseVo exceptionVo = new ResponseVo();
        if(e instanceof BusinessException){
            BusinessException ex = (BusinessException)e;
            exceptionVo.setMsg(ex.getErrorCode().getErrMessage());
            exceptionVo.setErrorCode(ex.getErrorCode().getErrCode());
        }else if(e instanceof IllegalArgumentException){
            exceptionVo.setMsg(e.getMessage());
            exceptionVo.setErrorCode(ErrorCodeDefinition.PARAM_PARSE_ERROR.getErrorCode().getErrCode());
        }else if(e instanceof IllegalStateException){
            exceptionVo.setMsg(ErrorCodeDefinition.ORDER_STATE_NOT_SUPPORT.getErrorCode().getErrMessage());
            exceptionVo.setErrorCode(ErrorCodeDefinition.ORDER_STATE_NOT_SUPPORT.getErrorCode().getErrCode());
        }else {
            exceptionVo.setMsg(ErrorCodeDefinition.DEFAULT_ERROR.getErrorCode().getErrMessage());
            exceptionVo.setErrorCode(ErrorCodeDefinition.DEFAULT_ERROR.getErrorCode().getErrCode());
        }
        write(response,exceptionVo);
        log.error(LOG_PRE_FIX.concat(ErrorCodeDefinition.DEFAULT_ERROR.getErrorCode().getErrMessage()));
    }

    /**
     * @param response
     * @param result <br>
     */
    protected void write(HttpServletResponse response, ResponseVo result) {
        PrintWriter out = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", result.isSuccess());
            map.put("msg", result.getMsg());
            map.put("errorCode", result.getErrorCode());

            String resStr = Base64.encodeBase64String(JsonUtil.toString(map).getBytes("utf-8"));
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Content-Type","application/json;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Methods", "*");
            out = response.getWriter();
            out.write(resStr);
        } catch (UnsupportedEncodingException e) {

        } catch (IOException e) {

        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }
}