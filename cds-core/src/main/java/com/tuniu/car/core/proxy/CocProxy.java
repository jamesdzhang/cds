package com.tuniu.car.core.proxy;

import com.tuniu.car.basic.exception.BusinessException;
import com.tuniu.car.basic.exception.ErrorCodeDefinition;
import com.tuniu.car.basic.param.build.intf.ICocOrderParam;
import com.tuniu.car.core.rest.client.RestExtUtil;
import com.tuniu.operation.platform.base.rest.RestException;
import com.tuniu.operation.platform.tsg.base.core.utils.JsonUtil;
import com.tuniu.car.basic.vo.response.ResponseVo;
import com.tuniu.operation.platform.tsg.client.proxy.tsg.TSPCommonClient;
import org.apache.commons.lang.UnhandledException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 与COC
 * Created by zhangyaping on 2017/5/22.
 */
@Component
public class CocProxy {

    private static Logger logger = LoggerFactory.getLogger(CocProxy.class);

    @Value("${coc.api.order.create}")
    private String CREATE_ORDER_URL;
    @Value("${coc.api.order.status.query}")
    private String QUERY_STATUS_URL;
    @Value("${coc.api.order.loss.check}")
    private String LOSS_CHECK_ORDER_URL;
    @Value("${coc.api.order.cancel}")
    private String CANCEL_ORDER_URL;

    private final String SYS_CODE = "COC";
    private final String TRACEABLE_LOG_TEMP = "【{0}】接口：【{1}】反馈：【{3}】";
    @Resource
    private TSPCommonClient tspCommonClient;

    public ResponseVo createOrder(ICocOrderParam param){
        ResponseVo vo = new ResponseVo();
        try{
            //coc该接口需要url传递参数.
            String result = RestExtUtil.sendData(null,CREATE_ORDER_URL.concat(URLEncoder.encode(JsonUtil.toString(param),
                    "UTF-8")), HttpMethod.GET.name(),false);
            logger.debug(TRACEABLE_LOG_TEMP,SYS_CODE,CREATE_ORDER_URL,result);
            vo = JsonUtil.toBean(result, ResponseVo.class);//代表调用成功
        }catch (Exception e){
            vo.setMsg(ErrorCodeDefinition.DEFAULT_ERROR.getErrorCode().getErrMessage());
            vo.setErrorCode(ErrorCodeDefinition.DEFAULT_ERROR.getErrorCode().getErrCode());
        }
        return vo;
    }

    public ResponseVo queryOrder(ICocOrderParam param){
        ResponseVo vo = new ResponseVo();
        try{
            //coc该接口需要url传递参数.
            String result = RestExtUtil.sendData(JsonUtil.toString(param),QUERY_STATUS_URL, HttpMethod.GET.name(),true);
            vo = JsonUtil.toBean(result, ResponseVo.class);//代表调用成功
        }catch (RuntimeException e){
            vo.setMsg(e.getMessage());//运行时异常先记录下信息，等下统一记录日志后包装处理
            vo.setErrorCode(ErrorCodeDefinition.DEFAULT_ERROR.getErrorCode().getErrCode());
        }
        return vo;
    }

    public ResponseVo lossCheck(ICocOrderParam param){
        ResponseVo vo = new ResponseVo();
        try{
            //coc该接口需要url传递参数.
            String result = RestExtUtil.sendData(null,LOSS_CHECK_ORDER_URL.concat(URLEncoder.encode(JsonUtil.toString(param),
                    "UTF-8")), HttpMethod.GET.name(),false);
            vo = JsonUtil.toBean(result, ResponseVo.class);//代表调用成功
        }catch (Exception e){
            vo.setMsg(e.getMessage());//运行时异常先记录下信息，等下统一记录日志后包装处理
            vo.setErrorCode(ErrorCodeDefinition.DEFAULT_ERROR.getErrorCode().getErrCode());
        }
        return vo;
    }

    public ResponseVo cancelOrder(ICocOrderParam param){
        ResponseVo vo = new ResponseVo();
        try{
            //coc该接口需要url传递参数.
            String result = RestExtUtil.sendData(null,CANCEL_ORDER_URL.concat(URLEncoder.encode(JsonUtil.toString(param),
                    "UTF-8")), HttpMethod.GET.name(),false);
            vo = JsonUtil.toBean(result, ResponseVo.class);//代表调用成功
            logger.debug(TRACEABLE_LOG_TEMP,SYS_CODE,CANCEL_ORDER_URL,result);
        }catch (Exception e){
            vo.setMsg(e.getMessage());//运行时异常先记录下信息，等下统一记录日志后包装处理
            vo.setErrorCode(ErrorCodeDefinition.DEFAULT_ERROR.getErrorCode().getErrCode());
        }
        return vo;
    }

}
