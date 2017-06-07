package com.tuniu.car.core.task.feedback;

import com.tuniu.car.core.business.IRevertBusiness;
import com.tuniu.car.core.redis.service.ConcurrentLock;
import com.tuniu.car.mapper.vo.CdsRevertVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyaping on 2017/6/9.
 */
@Service
public class FeedBackTask {

    private static Logger logger = LoggerFactory.getLogger(FeedBackTask.class);

    @Resource(name = "revertBusinessImpl")
    private IRevertBusiness revertBusiness;
    @Resource(name = "redisLock")
    private ConcurrentLock lock;


    @Scheduled(cron = "0 0/20 * * * ? ") // execute at every 20 minutes
    public void execute() {
        Map<String,Object> param = new HashMap<>();
        List<CdsRevertVo> toDoList = revertBusiness.retrieveRevertList(param,true);
        for(CdsRevertVo tmp : toDoList){
            if(lock.lock(tmp.getOrderId().concat(tmp.getRevertType()),60))
                revertBusiness.feedBackRecord(tmp,false);
        }
    }

}
