package com.tuniu.car.core.dao;

import com.tuniu.car.mapper.vo.CdsRevertVo;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangyaping on 2017/6/5.
 */
public interface ICdsRevertDao {

    List<CdsRevertVo> selectByParam(Map<String, Object> map);

    Integer insertRecord(CdsRevertVo cdsRequest);

    Integer updateResponse(CdsRevertVo cdsRequest);
}
