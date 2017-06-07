package com.tuniu.car.core.business;

import com.tuniu.car.basic.vo.request.OrderRequestDto;
import com.tuniu.car.mapper.vo.CdsRequestVo;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangyaping on 2017/6/6.
 */
public interface IPacketBusiness {

    void updateInterval(Long interval,String subOrderId, String packetType);

    void updateResponsePacket(CdsRequestVo vo);

    void addRecord(CdsRequestVo vo);

    List<CdsRequestVo> retrieveRecordByOrderId(String orderId);

    CdsRequestVo retrieveRecordByOrderIdAndType(String orderId, String packetType);

    List<CdsRequestVo> retrieveRecordByOrderIdAndType(String orderId, String packetType,boolean retrieveAll);

    List<CdsRequestVo> retrieveRecordByParam(CdsRequestVo vo);

    List<CdsRequestVo> retrieveRecordByParam(Map<String,Object> map);

    boolean isDuplicateRequest(OrderRequestDto requestDto);

    void deleteByParam(Map<String,Object> param);

}
