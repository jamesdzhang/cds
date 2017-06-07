package com.tuniu.car.core.dao;

import com.tuniu.car.mapper.vo.CdsRequestVo;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangyaping on 2017/6/5.
 */
public interface ICdsPacketDao {

    List<CdsRequestVo> selectByParam(Map<String,Object> map);

    Integer insertRecord(CdsRequestVo cdsRequest);

    Integer updateByParam(CdsRequestVo cdsRequest);
    
    Integer updateInterval(CdsRequestVo cdsRequest);

    Integer deleteByParam(Map<String,Object> map);
}
