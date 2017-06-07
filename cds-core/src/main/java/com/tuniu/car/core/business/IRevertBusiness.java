package com.tuniu.car.core.business;

import com.tuniu.car.basic.vo.request.OrderRequestDto;
import com.tuniu.car.basic.vo.response.ResponseVo;
import com.tuniu.car.mapper.vo.CdsRevertVo;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangyaping on 2017/6/6.
 */
public interface IRevertBusiness {

    /**
     * maybe we don't need OrderRequestDto as out input param
     * @param feedbackReq
     * @param responseVo
     */
    void addRevertRecord(OrderRequestDto feedbackReq, ResponseVo responseVo);

    void feedBackByOrderId(String orderId, String revertType, ResponseVo responseVo);

    /**
     *
     * @param revertInfo
     * @param isManually
     */
    void feedBackRecord(CdsRevertVo revertInfo, boolean isManually);

    Integer updateResponse(CdsRevertVo revertInfo);


    List<CdsRevertVo> retrieveRevertList(Map<String,Object> param, boolean canFeedback);


}
