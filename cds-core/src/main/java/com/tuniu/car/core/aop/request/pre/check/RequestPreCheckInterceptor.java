package com.tuniu.car.core.aop.request.pre.check;

import com.tuniu.car.basic.constants.ErrorMessages;
import com.tuniu.car.basic.enums.RequestTypeEnum;
import com.tuniu.car.basic.exception.ErrorCode;
import com.tuniu.car.basic.exception.ErrorCodeDefinition;
import com.tuniu.car.basic.vo.request.DomesticShuttleRequestDetail;
import com.tuniu.car.basic.vo.request.LossCheckRequestDetail;
import com.tuniu.car.basic.vo.request.OrderCancelRequestDetail;
import com.tuniu.car.basic.vo.request.OrderRequestDto;
import com.tuniu.car.core.business.IOrderBusiness;
import com.tuniu.car.core.business.IPacketBusiness;
import com.tuniu.car.core.redis.service.ConcurrentLock;
import com.tuniu.car.mapper.vo.CdsOrderVo;
import com.tuniu.car.mapper.vo.CdsRequestVo;
import com.tuniu.operation.platform.base.text.StringUtils;
import com.tuniu.car.basic.vo.response.ResponseVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by zhangyaping on 2017/5/22.
 */

@Aspect
@Component
public class RequestPreCheckInterceptor {

    private static Logger logger = LoggerFactory.getLogger(RequestPreCheckInterceptor.class);

    private final static String ERROR_MSG = "重复请求";

    @Resource(name = "orderBusinessImpl")
    private IOrderBusiness orderBusiness;

    @Resource(name = "packetBusinessImpl")
    private IPacketBusiness packetBusiness;

    @Resource(name = "redisLock")
    private ConcurrentLock lock;

    @Pointcut(value = "execution(* com.tuniu.car.distribute.apis.common.CarOrderApiController.*(..))")
    public void requestPoint() {}

    @Around("requestPoint()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        try{
            //请开始验证
            duplicateCheck(point);
            requestValidation(point);
        }catch (Exception ex){
            return rejectRequest(ex.getMessage());
        }
        //没有注解的放掉
        return point.proceed();
    }

    private ResponseVo rejectRequest(String errorMsg){
        ErrorCode code = ErrorCodeDefinition.DEFAULT_ERROR.dynamicMessage(
                ErrorMessages.PROXY_TIMEOUT_ERROR, errorMsg);
        if(errorMsg.equals(ERROR_MSG)){
            code = ErrorCodeDefinition.ORDER_DUPLICATE_REQUEST.dynamicMessage(ERROR_MSG);
        }
        ResponseVo errorVo = new ResponseVo();
        errorVo.setMsg(code.getErrMessage());
        errorVo.setErrorCode(code.getErrCode());
        return errorVo;
    }


    private void duplicateCheck(ProceedingJoinPoint point) throws Exception{
        OrderRequestDto requestParam = (OrderRequestDto)point.getArgs()[0];
        String requestKey = requestParam.getRequestId().concat(requestParam.getRequestType());
        if(!lock.lock(requestKey,60))
            throw new Exception(ERROR_MSG);
        //check the record
        recordCheck(requestParam);
    }

    private void recordCheck(OrderRequestDto requestParam) throws Exception{
        if(requestParam.getRequestDetail() instanceof OrderCancelRequestDetail){
            String  orderId = ((OrderCancelRequestDetail) requestParam.getRequestDetail()).getOrderId();
            CdsRequestVo tmpVo = packetBusiness.retrieveRecordByOrderIdAndType(orderId,requestParam.getRequestType());
            if(tmpVo!=null)
                throw new Exception(ErrorCodeDefinition.ORDER_DUPLICATE_REQUEST.getErrorCode().getErrMessage());
            else
                lock.lock(orderId.concat(requestParam.getRequestType()),60);
        } else if(requestParam.getRequestDetail() instanceof DomesticShuttleRequestDetail){
            CdsOrderVo tmpVo = orderBusiness.retrieveRecordByOrderId(requestParam.getRequestId(),true);
            if(tmpVo!=null)
                throw new Exception(ErrorCodeDefinition.ORDER_DUPLICATE_REQUEST.getErrorCode().getErrMessage());
        }
        //核损可请求多次
    }


    private void requestValidation(ProceedingJoinPoint point) throws Exception{
        OrderRequestDto requestParam = (OrderRequestDto)point.getArgs()[0];
        String orderId = null;
        String reqType = requestParam.getRequestType();
        if(requestParam.getRequestDetail() instanceof LossCheckRequestDetail){
            orderId = ((LossCheckRequestDetail) requestParam.getRequestDetail()).getOrderId();
        }else if(requestParam.getRequestDetail() instanceof OrderCancelRequestDetail){
            orderId = ((OrderCancelRequestDetail) requestParam.getRequestDetail()).getOrderId();
        }
        if(StringUtils.isNotEmpty(orderId)){
            //is supported request type and
            CdsOrderVo cdsOrderVo = orderBusiness.retrieveRecordByOrderId(orderId,false);
            String currentState = cdsOrderVo!=null?cdsOrderVo.getStatus():null;
            if(currentState==null || !RequestTypeEnum.codeOf(reqType).getSupportStatus().equals(currentState))// current request is not valid
                throw new Exception(ErrorCodeDefinition.ORDER_STATE_NOT_SUPPORT.getErrorCode().getErrMessage());
        }
    }

}
