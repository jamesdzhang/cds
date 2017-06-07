package com.tuniu.car.basic.exception.resolver;

import com.tuniu.operation.platform.tsg.base.core.utils.JsonUtil;
import com.tuniu.car.basic.vo.response.ResponseVo;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyaping on 2017/4/21.
 */

public class UnifiedExceptionResolver /*extends SimpleMappingExceptionResolver*/ {

    protected ModelAndView doResolveException(HttpServletRequest httpServletRequest,
                                              HttpServletResponse httpServletResponse, Object o, Exception e) {
        if(e instanceof IllegalStateException || e instanceof IllegalArgumentException){
            ResponseVo exceptionVo = new ResponseVo();
            exceptionVo.setMsg(e.getMessage());
            exceptionVo.setErrorCode(10000);//TODO error code
            write(httpServletResponse,exceptionVo);
        }else{
            ResponseVo exceptionVo = new ResponseVo();
            exceptionVo.setMsg("系统异常，请稍后重试");//TODO error code
            write(httpServletResponse,exceptionVo);
        }
        return null;
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
