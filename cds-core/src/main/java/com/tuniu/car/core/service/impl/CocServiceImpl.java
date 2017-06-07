package com.tuniu.car.core.service.impl;

import com.tuniu.car.basic.constants.CarOrderStateDefinition;
import com.tuniu.car.basic.constants.OrderState;
import com.tuniu.car.basic.exception.BusinessException;
import com.tuniu.car.basic.exception.ErrorCode;
import com.tuniu.car.basic.exception.ErrorCodeDefinition;
import com.tuniu.car.basic.mongo.db.CarOrderRequest;
import com.tuniu.car.basic.mongo.db.CarOrderResponse;
import com.tuniu.car.basic.param.build.director.CocOrderDirector;
import com.tuniu.car.basic.param.build.intf.CocOrderBuilder;
import com.tuniu.car.basic.param.build.intf.ICocOrderParam;
import com.tuniu.car.basic.vo.forward.coc.cancel.CocCancelDto;
import com.tuniu.car.basic.vo.forward.coc.create.MultiPrdCreateOrderInputDto;
import com.tuniu.car.basic.vo.forward.coc.loss.check.CocLossCheckDto;
import com.tuniu.car.basic.vo.forward.coc.loss.check.returned.CocLossDetailDto;
import com.tuniu.car.basic.vo.forward.coc.loss.check.returned.CocLossReturnedDto;
import com.tuniu.car.basic.vo.request.OrderQueryRequestDetail;
import com.tuniu.car.basic.vo.request.OrderQueryVo;
import com.tuniu.car.basic.vo.request.OrderRequestDto;
import com.tuniu.car.basic.vo.response.LossDetail;
import com.tuniu.car.basic.vo.response.OrderLossDetail;
import com.tuniu.car.basic.vo.response.OrderStatusVo;
import com.tuniu.car.core.business.IOrderBusiness;
import com.tuniu.car.core.business.IPacketBusiness;
import com.tuniu.car.core.proxy.CocProxy;
import com.tuniu.car.core.service.ICocService;
import com.tuniu.car.mapper.vo.CdsOrderVo;
import com.tuniu.car.mapper.vo.CdsRequestVo;
import com.tuniu.operation.platform.base.text.StringUtils;
import com.tuniu.operation.platform.tsg.base.core.utils.JsonUtil;
import com.tuniu.car.basic.vo.response.ResponseVo;
import com.tuniu.operation.platform.tsg.client.caller.rest.RequestParam;
import com.tuniu.operation.platform.tsg.client.proxy.tsg.TSPCommonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zhangyaping on 2017/5/22.
 */
@Service
public class CocServiceImpl implements ICocService{

    @Resource
    private CocProxy cocProxy;

    @Value("${envName}")
    private String envName;

    @Resource(name = "cocOrderBuildImpl")
    private CocOrderBuilder builder;

    @Resource(name = "packetBusinessImpl")
    private IPacketBusiness packetBusiness;

    @Resource(name = "orderBusinessImpl")
    private IOrderBusiness orderBusiness;

    private static final String CREATE_ORDER_SUCCESS_MSG = "下单成功，待支付";
    private static final String LOSS_CHECK_SUCCESS_MSG = "核损信息查询成功";
    private static final String CANCEL_SUCCESS_MSG = "取消成功";
    @Resource
    private TSPCommonClient tspCommonClient;

    @Override
    public ResponseVo createOrder(OrderRequestDto orderRequestDto) {
        ResponseVo resultVo;
        //转换参数
        try{
            CocOrderDirector cocOrderDirector = new CocOrderDirector(builder, orderRequestDto);
            cocOrderDirector.construct();
            MultiPrdCreateOrderInputDto cocParam = (MultiPrdCreateOrderInputDto)builder.getResult();
            //调用coc下单接口
            resultVo = cocProxy.createOrder(cocParam);
            //组装
            OrderStatusVo orderStatusVo = new OrderStatusVo();
            if(resultVo.isSuccess()){
                resultVo.setSuccess(true);
                resultVo.setMsg(CREATE_ORDER_SUCCESS_MSG);
                orderStatusVo.setOrderId(String.valueOf(resultVo.getData()));
                OrderState state = CarOrderStateDefinition.getStateBySysCode(OrderState.CREATED);
                orderStatusVo.setStatusName(state.getStateDesc());
                orderStatusVo.setStatusCode(state.getStateCode());
                afterCreation(orderStatusVo.getOrderId());
            }else{
                ErrorCode errorCode= ErrorCodeDefinition.find(resultVo.getErrorCode());
                resultVo.setErrorCode(errorCode.getErrCode());
                resultVo.setMsg(errorCode.getErrMessage());
            }
            resultVo.setData(orderStatusVo);
            //init record
            initRecord(orderRequestDto, cocParam, resultVo, orderStatusVo.getOrderId());
        }catch (Exception ex){
            throw ex;
        }
        return resultVo;
    }

    @Override
    public ResponseVo lossCheck(OrderRequestDto orderRequestDto) {
        ResponseVo resultVo;
        //转换参数
        try{
            CocOrderDirector cocOrderDirector = new CocOrderDirector(builder, orderRequestDto);
            cocOrderDirector.construct();
            CocLossCheckDto cocParam = (CocLossCheckDto)builder.getResult();
            //调用coc下单接口
            resultVo = cocProxy.lossCheck(cocParam);
            //组装
            OrderLossDetail lossCheckDto = new OrderLossDetail();
            if(resultVo.isSuccess()){
                resultVo.setSuccess(true);
                resultVo.setMsg(LOSS_CHECK_SUCCESS_MSG);
                lossCheckDto.setOrderId(String.valueOf(cocParam.getOrderId()));
                OrderState state = CarOrderStateDefinition.getStateBySysCode(OrderState.LOSS_CHECKED);
                lossCheckDto.setStatusName(state.getStateDesc());
                lossCheckDto.setStatusCode(state.getStateCode());
                lossCheckDto.setLossSchemeId(orderRequestDto.getRequestSeq());
                CocLossReturnedDto lossReturnedDto = JsonUtil.toBean(resultVo.getData(),CocLossReturnedDto.class);
                lossCheckDto.setLossDetail(translate2SysLossInfo(lossReturnedDto.getLossData()));
                //update status in main table
                updateStatus(String.valueOf(cocParam.getOrderId()),OrderState.LOSS_CHECKED);
            }else{
                ErrorCode errorCode= ErrorCodeDefinition.find(resultVo.getErrorCode());
                resultVo.setErrorCode(errorCode.getErrCode());
                resultVo.setMsg(errorCode.getErrMessage());
            }
            resultVo.setData(lossCheckDto);
            addRequestRecord(orderRequestDto, cocParam, resultVo,String.valueOf(cocParam.getOrderId()),resultVo.isSuccess());
        }catch (Exception ex){
            throw ex;
        }
        return resultVo;
    }

    @Override
    public ResponseVo cancelOrder(OrderRequestDto orderRequestDto) {
        ResponseVo resultVo;
        //转换参数
        try{
            CocOrderDirector cocOrderDirector = new CocOrderDirector(builder, orderRequestDto);
            cocOrderDirector.construct();
            CocCancelDto cocParam = (CocCancelDto)builder.getResult();
            //调用coc下单接口
            resultVo = cocProxy.cancelOrder(cocParam);
            //组装
            OrderStatusVo orderStatusVo = new OrderStatusVo();
            if(resultVo.isSuccess()){
                resultVo.setSuccess(true);
                resultVo.setMsg(CANCEL_SUCCESS_MSG);
                orderStatusVo.setOrderId(String.valueOf(cocParam.getOrderId()));
                OrderState state = CarOrderStateDefinition.getStateBySysCode(OrderState.CANCELED);
                orderStatusVo.setStatusName(state.getStateDesc());
                orderStatusVo.setStatusCode(state.getStateCode());
                //update status in main table
                updateStatus(String.valueOf(cocParam.getOrderId()),OrderState.LOSS_CHECKED);
            }else{
                ErrorCode errorCode= ErrorCodeDefinition.find(resultVo.getErrorCode());
                resultVo.setErrorCode(errorCode.getErrCode());
                resultVo.setMsg(errorCode.getErrMessage());
            }
            resultVo.setData(orderStatusVo);
            addRequestRecord(orderRequestDto, cocParam, resultVo,String.valueOf(cocParam.getOrderId()),resultVo.isSuccess());
        }catch (Exception ex){
            throw ex;
        }
        return resultVo;
    }

    @Override
    public ResponseVo queryStatus(OrderRequestDto orderRequestDto) {
        ResponseVo resultVo = new ResponseVo();
        List<OrderQueryVo> cocParams = ((OrderQueryRequestDetail)orderRequestDto.getRequestDetail()).getTnOrderList();
        List<OrderStatusVo> resultList = new ArrayList<>(cocParams.size());
        for(OrderQueryVo tmp : cocParams){
            OrderState state;
            try{
                CdsOrderVo tmpVo = orderBusiness.retrieveRecordByOrderId(tmp.getOrderId(),false);
                state = CarOrderStateDefinition.getStateBySysCode(tmpVo.getStatus());
                //blow is request for order status in COC system
                /*ResponseVo tmpResultVo = cocProxy.queryOrder(tmp);
                StmOrdStatus tmpResult = JsonUtil.toBean(tmpResultVo.getData(),StmOrdStatus.class);
                if(tmpResultVo.isSuccess())
                    state = CarOrderStateDefinition.getStateByCocCode(tmpResult.getStatusCode());
                else
                    throw new BusinessException(ErrorCodeDefinition.DEFAULT_ERROR.getErrorCode());*/
            }catch (Exception e){
                state = CarOrderStateDefinition.getStateBySysCode(OrderState.UNKNOWN);
            }
            resultList.add(translate2SysOrderStatus(state, tmp.getOrderId()));
        }
        resultVo.setSuccess(true);
        resultVo.setData(resultList);
        return resultVo;
    }

    @Override
    public ResponseVo feedback(OrderRequestDto orderRequestDto) {
        return null;
    }


    private OrderStatusVo translate2SysOrderStatus(OrderState state, String orderId){
        OrderStatusVo sysStatus = new OrderStatusVo();
        sysStatus.setStatusCode(state.getStateCode());
        sysStatus.setStatusName(state.getStateDesc());
        sysStatus.setOrderId(orderId);
        return sysStatus;
    }

    private LossDetail translate2SysLossInfo(CocLossDetailDto lossDetailDto){
        LossDetail lossDetail1 = new LossDetail();
        lossDetail1.setLossAmount(lossDetailDto.getBreakContractPrice());
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE,1);
        lossDetail1.setExpireTime(lossDetailDto.getEffectiveTime()==null?
                c.getTime():lossDetailDto.getEffectiveTime());
        return lossDetail1;
    }

    private void initRecord(OrderRequestDto orderRequestDto, ICocOrderParam cocParam, ResponseVo resultVo, String orderId){
        //step1 add into main table
        boolean addMainRecordDone = addOrderRecord(orderRequestDto,orderId);
        //step2 add into request table if needed
        if( addMainRecordDone && StringUtils.isNotEmpty(orderId))
            addRequestRecord(orderRequestDto, cocParam, resultVo,orderId,true);
    }

    private boolean addOrderRecord(OrderRequestDto orderRequestDto, String orderId){
        CdsOrderVo orderVo = new CdsOrderVo();
        orderVo.setSubOrderId(orderRequestDto.getRequestId());
        orderVo.setReqSource(orderRequestDto.getRequestSource());
        if(StringUtils.isNotEmpty(orderId)){
            orderVo.setOrderId(orderId);
            orderVo.setStatus(OrderState.CREATED);
        }else {//还需要记录下来失败的预定请求，暂定这种情形下orderId为000
            orderVo.setOrderId("000");
            orderVo.setStatus(OrderState.UNKNOWN);
        }
        return orderBusiness.addRecord(orderVo)>0; //successfully added in main table
    }
    private void addRequestRecord(OrderRequestDto orderRequestDto,ICocOrderParam cocParam,
                                  ResponseVo resultVo, String orderId, boolean isSuccess){
        CdsRequestVo packVo = new CdsRequestVo();
        packVo.setOrderId(orderId);
        packVo.setReqSeq(orderRequestDto.getRequestSeq());
        packVo.setReqType(orderRequestDto.getRequestType());
        CarOrderRequest<OrderRequestDto> reqPack = new CarOrderRequest<>();
        reqPack.setRequestDetail(orderRequestDto);
        packVo.setReqPacket(reqPack);
        CarOrderRequest<ICocOrderParam> reqCocPack = new CarOrderRequest<>();
        reqCocPack.setRequestDetail(cocParam);
        packVo.setReqPacketCoc(reqCocPack);
        CarOrderResponse<ResponseVo> respCocPack = new CarOrderResponse<>();
        respCocPack.setResponseDetail(resultVo);
        packVo.setRespPacket(respCocPack);
        packVo.setNormal(isSuccess?1:0);
        packetBusiness.addRecord(packVo);
    }

    private void updateStatus(String orderId, String statusCode){
        //lock first

        CdsOrderVo vo = new CdsOrderVo();
        vo.setOrderId(orderId);
        vo.setStatus(statusCode);
        orderBusiness.updateStatus(vo);
    }

    /**
     * when profile is test, mock payment for distributors which they can not
     */
    private void afterCreation(String orderId){
        if(envName.equals("test")){
            Map<String,Object> param = new HashMap<>();
            param.put("orderId",orderId);
            param.put("pay",10000);
            RequestParam requestParam = new RequestParam();
            requestParam.setServiceName("CAR.COC.PaymentController.payApplyFeedback");
            requestParam.setData(JsonUtil.toString(param));
            try{
                tspCommonClient.responseCaller(requestParam);
            }catch (Exception e){
                throw new BusinessException(ErrorCodeDefinition.SIT_MOCK_PAYMENT_FAILED.getErrorCode(),orderId);
            }
        }
    }

}
