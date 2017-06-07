package com.tuniu.car.core.business;

import com.tuniu.car.mapper.vo.CdsOrderVo;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangyaping on 2017/6/6.
 */
public interface IOrderBusiness {

    void updateStatus(CdsOrderVo vo);

    Integer addRecord(CdsOrderVo vo);

    CdsOrderVo retrieveRecordByOrderId(String orderId, boolean isSubOrder);

    List<CdsOrderVo> retrieveRecordByParam(CdsOrderVo vo);

    List<CdsOrderVo> retrieveRecordByParam(Map<String, Object> map);

}