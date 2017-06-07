package com.tuniu.car.core.amq.recieve.order.status;

import com.tuniu.operation.platform.base.text.StringUtils;
import com.tuniu.operation.platform.tsg.base.core.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 状态机订单队列接受
 * From COC
 * @author liuhaitao
 * 
 */
@Service
public class CocOrderStatusMsgReceiver implements MessageListener {

    private static Logger logger = LoggerFactory.getLogger(CocOrderStatusMsgReceiver.class);

    @Value(value = "@{coc.order.status.queue}")
    private String cocOrderQueue;

    @Override
    public void onMessage(Message message) {
        String msg = null;
        if (message instanceof TextMessage) {
            try {
                msg = ((TextMessage) message).getText();
                logger.info(">>>>>>>>>>  StmMsgReceiver msg=" + msg);
                if (StringUtils.isBlank(msg)) {
                    return;
                }

                StmMqParamDto stmMqParamDto = JsonUtil.toBean(msg, StmMqParamDto.class);
                if (stmMqParamDto != null) {
                    logger.info("######## 1 ######### StmMsgReceiver msg={} 消息开始执行", msg);

                    boolean isFromCDS = !"CDS".equals(stmMqParamDto.getResourceChannel());
                    if (isFromCDS) {
                        //CDS的订单消息才做处理
                        logger.info("【deal with order status msg】:",msg);
                        logger.info("######## 2 ######### StmMsgReceiver msg={} 消息结束执行", msg);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                logger.info("************** StmMsgReceiver msg={} 消息执行异常,error={}", msg, e.getMessage());
            }

        } else {
            logger.error("Message must be of type TextMessage:{}", message);
        }

    }
}