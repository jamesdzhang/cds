package com.tuniu.car.core.dao.impl;

import com.tuniu.car.core.dao.ICdsPacketDao;
import com.tuniu.car.mapper.vo.CdsRequestVo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyaping on 2017/6/5.
 */
@Repository
public class CdsPacketDaoImpl implements ICdsPacketDao {

    private static String DEFAULT_NAMESPACE = CdsRequestVo.class.getName().concat(".");

    @Resource(name = "sqlSessionTemplateMaster")
    private SqlSessionTemplate sst;


    @Override
    public List<CdsRequestVo> selectByParam(Map<String, Object> map) {
        return this.sst.selectList(DEFAULT_NAMESPACE.concat("selectByParam"), map);
    }

    @Override
    public Integer insertRecord(CdsRequestVo cdsRequest) {
        return this.sst.insert(DEFAULT_NAMESPACE.concat("insertRecord"), cdsRequest);
    }

    @Override
    public Integer updateByParam(CdsRequestVo cdsRequest) {
        return this.sst.update(DEFAULT_NAMESPACE.concat("updateByParam"), cdsRequest);
    }

    @Override
    public Integer updateInterval(CdsRequestVo cdsRequest) {
        return this.sst.update(DEFAULT_NAMESPACE.concat("updateInterval"), cdsRequest);
    }

    @Override
    public Integer deleteByParam(Map<String, Object> map) {
        return this.sst.delete(DEFAULT_NAMESPACE.concat("deleteByParam"), map);
    }
}
