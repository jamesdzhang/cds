package com.tuniu.car.core.redis.service;

import com.tuniu.operation.platform.tsg.base.core.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class CarRedisService {
	
	private static final Logger logger = LoggerFactory.getLogger(CarRedisService.class);
	
	@Resource
    StringRedisTemplate stringRedisTemplate;
	

    public  void saveToRedis(String key, Object value, long expireTime){
        BoundValueOperations<String, String> op = stringRedisTemplate.boundValueOps(key);
    	try {
            op.getAndSet(JsonUtil.toString(value));
            if (op.getExpire() < 0) {
                op.expire(expireTime, TimeUnit.MILLISECONDS);
            }
        } catch (Exception e) {
            logger.error("put result into cache error,e:{}"+ JsonUtil.toString(e));
        }
    }
    
    
    public <T> T queryFromRedis(String redisKey, Class<T> clz){
    	T result = null;
       
        String hashedValue = null;
        final ValueOperations<String, String> op = stringRedisTemplate.opsForValue();
        try {
            hashedValue = op.get(redisKey);
        } catch (Exception e) {
            logger.error("redis Connection refused " , e);
            return null;
        }
        /*if (StringUtils.isNotEmpty(hashedValue)) {
            result = JsonUtil.toBean(hashedValue, clz);
        }*/
    	return result;
    }

    public String queryFromRedis(String redisKey){
        String hashedValue = null;
        final ValueOperations<String, String> op = stringRedisTemplate.opsForValue();
        try {
            hashedValue = op.get(redisKey);
        } catch (Exception e) {
            logger.error("redis Connection refused " , e);
            return null;
        }
        return hashedValue;
    }
}
