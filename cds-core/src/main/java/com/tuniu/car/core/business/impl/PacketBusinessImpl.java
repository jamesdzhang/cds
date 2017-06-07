package com.tuniu.car.core.business.impl;

import com.tuniu.car.basic.vo.request.OrderRequestDto;
import com.tuniu.car.core.business.IPacketBusiness;
import com.tuniu.car.core.dao.ICdsOrderDao;
import com.tuniu.car.core.dao.ICdsPacketDao;
import com.tuniu.car.mapper.vo.CdsRequestVo;
import com.tuniu.operation.platform.tsg.base.core.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class PacketBusinessImpl implements IPacketBusiness {

    private static Logger logger = LoggerFactory.getLogger(PacketBusinessImpl.class);

    @Resource(name = "cdsPacketDaoImpl")
    private ICdsPacketDao cdsPacketDao;
    @Resource(name = "cdsOrderDaoImpl")
    private ICdsOrderDao cdsOrderDao;

    private final static String NOT_NULL_MSG = "参数不能为空";

    @Override
    public void updateInterval(Long interval, String reqSeq, String packetType) {
        try {
            Assert.notNull(reqSeq, NOT_NULL_MSG);
            Assert.notNull(packetType, NOT_NULL_MSG);
            CdsRequestVo param = new CdsRequestVo();
            param.setReqInterval(interval);
            param.setReqSeq(reqSeq);
            param.setReqType(packetType);
            cdsPacketDao.updateInterval(param);
        } catch (Exception e) {
            logger.error("update interval, input[{0}],exception[{1}]", reqSeq.concat("-").concat(packetType), e.getMessage());
        }
    }

    @Override
    public void updateResponsePacket(CdsRequestVo vo) {
        try {
            Assert.notNull(vo, NOT_NULL_MSG);
            Assert.notNull(vo.getRespPacket(), NOT_NULL_MSG);
            CdsRequestVo param = new CdsRequestVo();
            param.setRespPacket(vo.getRespPacket());
            param.setRespPacketCoc(vo.getRespPacketCoc());
            cdsPacketDao.updateByParam(param);
        } catch (Exception e) {
            logger.error("update response packet failed, input[{0}],exception[{1}]", vo, e.getMessage());
        }
    }

    @Override
    public void addRecord(CdsRequestVo vo) {
        try {
            Assert.notNull(vo, NOT_NULL_MSG);
            cdsPacketDao.insertRecord(vo);
        } catch (Exception e) {
            logger.error("add record failed, input[{0}],exception[{1}]", vo, e.getMessage());
        }
    }

    @Override
    public List<CdsRequestVo> retrieveRecordByOrderId(String orderId) {
        try {
            Assert.notNull(orderId, NOT_NULL_MSG);
            Map<String, Object> param = new HashMap<>();
            param.put("orderId", orderId);
            List<CdsRequestVo> list = cdsPacketDao.selectByParam(param);
            return cdsPacketDao.selectByParam(param);
        } catch (Exception e) {
            logger.error("retrieve record by orderId failed, input[{0}],exception[{1}]", orderId, e.getMessage());
            return null;
        }
    }

    @Override
    public CdsRequestVo retrieveRecordByOrderIdAndType(String orderId, String packetType) {
        try {
            Assert.notNull(orderId, NOT_NULL_MSG);
            Map<String, Object> param = new HashMap<>();
            param.put("orderId", orderId);
            param.put("reqType", packetType);
            List<CdsRequestVo> list = cdsPacketDao.selectByParam(param);
            if (list != null && list.size() > 0)
                return list.get(0);
            else
                return null;
        } catch (Exception e) {
            logger.error("retrieve record by orderId failed, input[{0}],exception[{1}]", orderId.concat("-").concat(packetType),
                    e.getMessage());
            return null;
        }
    }

    @Override
    public List<CdsRequestVo> retrieveRecordByOrderIdAndType(String orderId, String packetType, boolean retrieveAll) {
        try {
            Assert.notNull(orderId, NOT_NULL_MSG);
            Map<String, Object> param = new HashMap<>();
            param.put("orderId", orderId);
            param.put("reqType", packetType);
            List<CdsRequestVo> list = cdsPacketDao.selectByParam(param);
            if (list != null && list.size() > 0)
                return list;
            else
                return null;
        } catch (Exception e) {
            logger.error("retrieve record by orderId failed, input[{0}],exception[{1}]", orderId.concat("-").concat(packetType),
                    e.getMessage());
            return null;
        }
    }

    @Override
    public List<CdsRequestVo> retrieveRecordByParam(CdsRequestVo vo) {
        try {
            Assert.notNull(vo, NOT_NULL_MSG);
            return cdsPacketDao.selectByParam(JsonUtil.toBean(vo, Map.class));
        } catch (Exception e) {
            logger.error("retrieve record by param failed, input[{0}],exception[{1}]", vo, e.getMessage());
            return null;
        }
    }

    @Override
    public List<CdsRequestVo> retrieveRecordByParam(Map<String, Object> map) {
        try {
            Assert.notNull(map, NOT_NULL_MSG);
            return cdsPacketDao.selectByParam(map);
        } catch (Exception e) {
            logger.error("retrieve record by param failed, input[{0}],exception[{1}]", map, e.getMessage());
            return null;
        }
    }

    @Override
    public boolean isDuplicateRequest(OrderRequestDto requestDto) {
        try {
            Assert.notNull(requestDto, NOT_NULL_MSG);
            CdsRequestVo param = new CdsRequestVo();
            param.setReqType(requestDto.getRequestType());
            param.setOrderId("");
//            cdsPacketDao.selectByParam(JsonUtil.toBean(vo,Map.class));
        } catch (Exception e) {
            logger.error("duplicate check failed, input[{0}],exception[{1}]", requestDto, e.getMessage());
        }
        return false;
    }

    @Override
    public void deleteByParam(Map<String,Object> param) {
        try {
            Assert.notNull(param, NOT_NULL_MSG);
            cdsPacketDao.deleteByParam(param);
        } catch (Exception e) {
            logger.error("delete by param failed, input[{0}],exception[{1}]", param, e.getMessage());
        }
    }
}
