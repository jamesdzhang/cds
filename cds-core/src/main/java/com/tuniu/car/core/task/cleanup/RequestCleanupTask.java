package com.tuniu.car.core.task.cleanup;

import com.tuniu.car.basic.constants.OrderState;
import com.tuniu.car.basic.enums.RequestTypeEnum;
import com.tuniu.car.core.business.IOrderBusiness;
import com.tuniu.car.core.business.IPacketBusiness;
import com.tuniu.car.mapper.vo.CdsOrderVo;
import com.tuniu.car.mapper.vo.CdsRequestVo;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zhangyaping on 2017/6/7.
 * This task is to clean up the extra loss check request.
 */
@Service
public class RequestCleanupTask {

    private static Logger logger = LoggerFactory.getLogger(RequestCleanupTask.class);
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Resource(name = "orderBusinessImpl")
    private IOrderBusiness orderBusiness;

    @Resource(name = "packetBusinessImpl")
    private IPacketBusiness packetBusiness;

    @Scheduled(cron = "0 15 01 * * ? ") // execute at 01:15am every morning
    public void execute(){
        Map<String,Object> param = new HashMap<>();
        param.put("status", OrderState.CANCELED);
        param.put("startDate", DateFormatUtils.format(Calendar.getInstance().getTime(),DATE_FORMAT));
//        param.put("status", OrderState.CREATED);  //Test
//        param.put("startDate", "2017-06-01");  //Test
        List<CdsOrderVo> cleanupList = orderBusiness.retrieveRecordByParam(param);
        Set<Integer> ids = new HashSet<>();
        for(CdsOrderVo tmp : cleanupList){
            List<CdsRequestVo> requestVoList = packetBusiness.retrieveRecordByOrderIdAndType(
                    tmp.getOrderId(), RequestTypeEnum.LOSS_CHECK.getCode(),true);
            for(int index=0; requestVoList!=null && index< requestVoList.size()-1; index++){
                ids.add(requestVoList.get(index).getId());
            }
        }
        if(ids.size()>0){
            param = new HashMap<>();
            param.put("ids",ids);
            packetBusiness.deleteByParam(param);
        }


    }

}
