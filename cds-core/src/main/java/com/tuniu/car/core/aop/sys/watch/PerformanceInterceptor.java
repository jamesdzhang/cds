package com.tuniu.car.core.aop.sys.watch;

import com.tuniu.car.basic.vo.request.OrderRequestDto;
import com.tuniu.car.core.business.IPacketBusiness;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;

/**
 * Created by zhangyaping on 2017/5/22.
 * Watch over all the apis invoking time
 */

@Aspect
@Component
public class PerformanceInterceptor {

    private static Logger logger = LoggerFactory.getLogger(PerformanceInterceptor.class);

    @Resource(name = "packetBusinessImpl")
    private IPacketBusiness packetBusiness;

    @Pointcut(value = "execution(* com.tuniu.car.distribute.apis.common.CarOrderApiController.*(..))")
    public void performancePoint() {}

    @Around("performancePoint()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Long startTime = Calendar.getInstance().getTimeInMillis();
        Object result = point.proceed();
        Long interval = Calendar.getInstance().getTimeInMillis() - startTime;
        logger.debug("【{0}】running interval:{1}ms",
                point.getSignature().getDeclaringTypeName().concat(point.getSignature().getName()),interval);
        OrderRequestDto requestParam = (OrderRequestDto)point.getArgs()[0];
        packetBusiness.updateInterval(interval,requestParam.getRequestSeq(),requestParam.getRequestType());
        return result;
    }

}
