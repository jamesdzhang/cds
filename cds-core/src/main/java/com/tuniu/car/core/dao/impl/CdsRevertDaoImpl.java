package com.tuniu.car.core.dao.impl;

import com.tuniu.car.core.dao.ICdsRevertDao;
import com.tuniu.car.mapper.vo.CdsRevertVo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyaping on 2017/6/5.
 */
@Repository
public class CdsRevertDaoImpl implements ICdsRevertDao {

    private static String DEFAULT_NAMESPACE = CdsRevertVo.class.getName().concat(".");

    @Resource(name = "sqlSessionTemplateMaster")
    private SqlSessionTemplate sst;


    @Override
    public List<CdsRevertVo> selectByParam(Map<String, Object> map) {
        return this.sst.selectList(DEFAULT_NAMESPACE.concat("selectByParam"), map);
    }

    @Override
    public Integer insertRecord(CdsRevertVo cdsRequest) {
        return this.sst.insert(DEFAULT_NAMESPACE.concat("insertRecord"), cdsRequest);
    }

    @Override
    public Integer updateResponse(CdsRevertVo cdsRequest) {
        return this.sst.update(DEFAULT_NAMESPACE.concat("updateResponse"), cdsRequest);
    }

}
