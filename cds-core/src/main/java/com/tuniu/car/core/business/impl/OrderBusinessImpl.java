package com.tuniu.car.core.business.impl;

import com.tuniu.car.core.business.IOrderBusiness;
import com.tuniu.car.core.dao.ICdsOrderDao;
import com.tuniu.car.core.redis.service.ConcurrentLock;
import com.tuniu.car.mapper.vo.CdsOrderVo;
import com.tuniu.operation.platform.tsg.base.core.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyaping on 2017/6/6.
 */
@Service
public class OrderBusinessImpl implements IOrderBusiness {

    private static Logger logger = LoggerFactory.getLogger(OrderBusinessImpl.class);

    @Resource(name = "cdsOrderDaoImpl")
    private ICdsOrderDao cdsOrderDao;

    @Resource(name = "redisLock")
    private ConcurrentLock lock;

    private final static String NOT_NULL_MSG = "参数不能为空";

    @Override
    public void updateStatus(CdsOrderVo vo) {
        try{
            Assert.notNull(vo,NOT_NULL_MSG);
            //get distributing lock before updating take place
//            if(lock.lock(vo.getOrderId().concat(vo.getReqSource()),60))
                cdsOrderDao.updateStatus(vo);
//            else
//                throw new Exception("get lock failed");
        }catch (Exception e){
            logger.error("status update failed, input[{0}],exception[{1}]",vo,e.getMessage());
        }
    }

    @Override
    public Integer addRecord(CdsOrderVo vo) {
        try{
            Assert.notNull(vo,NOT_NULL_MSG);
            return cdsOrderDao.insertRecord(vo);
        }catch (Exception e){
            logger.error("insert failed, input[{0}],exception[{1}]",vo,e.getMessage());
            return -1;
        }
    }

    @Override
    public CdsOrderVo retrieveRecordByOrderId(String orderId, boolean isSubOrder) {
        try{
            Assert.notNull(orderId,NOT_NULL_MSG);
            if(isSubOrder)
                return cdsOrderDao.selectBySubOrderId(orderId);
            else
                return cdsOrderDao.selectByOrderId(orderId);
        }catch (Exception e){
            logger.error("retrieve record by orderId failed, input[{0}],exception[{1}]",orderId+'-'+isSubOrder,e.getMessage());
            return null;
        }
    }

    @Override
    public List<CdsOrderVo> retrieveRecordByParam(CdsOrderVo vo) {
        try{
            Assert.notNull(vo,NOT_NULL_MSG);
            return (List<CdsOrderVo>)cdsOrderDao.selectByParam(JsonUtil.toBean(vo,Map.class));
        }catch (Exception e){
            logger.error("retrieve record by param failed, input[{0}],exception[{1}]",vo,e.getMessage());
            return null;
        }
    }

    @Override
    public List<CdsOrderVo> retrieveRecordByParam(Map<String,Object> map) {
        try{
            return cdsOrderDao.selectByParam(map);
        }catch (Exception e){
            logger.error("retrieve record by param failed, input[{0}],exception[{1}]",map,e.getMessage());
            return null;
        }
    }
}
