package com.tuniu.car.core.business.impl;

import com.tuniu.car.basic.mongo.db.CarOrderRequest;
import com.tuniu.car.basic.mongo.db.CarOrderResponse;
import com.tuniu.car.basic.vo.request.OrderRequestDto;
import com.tuniu.car.basic.vo.response.ResponseVo;
import com.tuniu.car.core.business.IRevertBusiness;
import com.tuniu.car.core.dao.ICdsOrderDao;
import com.tuniu.car.core.dao.ICdsPacketDao;
import com.tuniu.car.core.dao.ICdsRevertDao;
import com.tuniu.car.mapper.vo.CdsOrderVo;
import com.tuniu.car.mapper.vo.CdsRevertVo;
import com.tuniu.operation.platform.tsg.base.core.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyaping on 2017/6/6.
 */
@Service
public class RevertBusinessImpl implements IRevertBusiness {

    private static Logger logger = LoggerFactory.getLogger(RevertBusinessImpl.class);

    @Resource(name = "cdsOrderDaoImpl")
    private ICdsOrderDao cdsOrderDao;
    @Resource(name = "cdsRevertDaoImpl")
    private ICdsRevertDao cdsRevertDao;

    @Value("${feedback.maximum.times}")
    private Integer MAX_RETRY_TIMES;

    private static final Integer FEEDBACK_SUCCESS = 1;
    private static final Integer FEEDBACK_FAIL = 0;
    private static final Integer FEEDBACK_TOTAL_FAIL = -1;

    private final static String NOT_NULL_MSG = "参数不能为空";

    @Override
    public void addRevertRecord(OrderRequestDto feedbackReq, ResponseVo responseVo) {
        try{
            CdsOrderVo orderVo = cdsOrderDao.selectBySubOrderId(feedbackReq.getRequestId());
            CdsRevertVo vo = new CdsRevertVo();
            vo.setOrderId(orderVo.getOrderId());
            vo.setSubOrderId(feedbackReq.getRequestId());
            vo.setRevertType(feedbackReq.getRequestType());
            CarOrderRequest<OrderRequestDto> feedBackReqPacket = new CarOrderRequest<>();
            feedBackReqPacket.setRequestDetail(feedbackReq);
            vo.setReqPacket(feedBackReqPacket);
            if(responseVo != null){
                CarOrderResponse<ResponseVo> feedBackRespPacket = new CarOrderResponse<>();
                feedBackRespPacket.setResponseDetail(responseVo);
                vo.setRespPacket(feedBackRespPacket);
            }
//            vo.setRetryTimes(1); // initialization only so, the retry times should not be increased
            cdsRevertDao.insertRecord(vo);
        }catch (Exception e){
            logger.error("feedback failed【{0}】，【{1}】", JsonUtil.toString(feedbackReq),e.getMessage());
            //TODO add a record to warning table, if also failed, send warning msg instantly
        }
    }

    @Override
    public void feedBackByOrderId(String orderId, String revertType, ResponseVo responseVo) {
        try{
            Assert.notNull(orderId,NOT_NULL_MSG);
            Assert.notNull(revertType,NOT_NULL_MSG);

            Map<String,Object> param = new HashMap<>();
            param.put("orderId",orderId);
            param.put("revertType",revertType);
            List<CdsRevertVo> revertList = retrieveRevertList(param,true);
            for(CdsRevertVo tmp : revertList){
                feedBackRecord(tmp,true);
            }
        }catch (Exception e){
            logger.error("feedback failed 【{0}】，【{1}】", JsonUtil.toString(orderId),e.getMessage());
            //TODO add a record to warning table, if also failed, send warning msg instantly
        }
    }

    @Override
    public void feedBackRecord(CdsRevertVo revertInfo, boolean isManually) {
        try{
            int retriedTime = isManually? 0: revertInfo.getRetryTimes()+1;

            //TODO feedback via a tsp proxy, take note of
            // response = proxyClass.feedback()
            //revertInfo.setRespPacket(response)
            //revertInfo.setIsSuccess(FEEDBACK_SUCCESS); or FEEDBACK_FAIL

            if(retriedTime >= MAX_RETRY_TIMES && revertInfo.getIsSuccess() == FEEDBACK_FAIL) //resp为空说明没有反馈成功
                retriedTime = FEEDBACK_TOTAL_FAIL;
            revertInfo.setRetryTimes(retriedTime);

            updateResponse(revertInfo);
        }catch (Exception ex){
            logger.error("feedback record failed 【{0}】，【{1}】", JsonUtil.toString(revertInfo),ex.getMessage());
            //TODO add a record to warning table, if also failed, send warning msg instantly
        }
    }

    @Override
    public List<CdsRevertVo> retrieveRevertList(Map<String, Object> param, boolean canFeedback) {
        try{
            if(canFeedback)
                param.put("available",true);
            else
                param.put("failed",true);
            return  cdsRevertDao.selectByParam(param);
        }catch (Exception ex){
            logger.error("retrieve revert list 【{0}】，【{1}】", JsonUtil.toString(param),ex.getMessage());
            return null;
        }
    }

    @Override
    public Integer updateResponse(CdsRevertVo revertInfo) {
        try {
            Assert.notNull(revertInfo, NOT_NULL_MSG);
            return cdsRevertDao.updateResponse(revertInfo);
        } catch (Exception e) {
            logger.error("retrieve record by param failed, input[{0}],exception[{1}]", revertInfo, e.getMessage());
            return null;
        }
    }

}
