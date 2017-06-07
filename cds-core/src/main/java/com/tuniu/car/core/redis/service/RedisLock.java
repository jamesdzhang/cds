package com.tuniu.car.core.redis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;

import com.tuniu.car.basic.exception.BusinessException;
import com.tuniu.car.basic.exception.ErrorCodeDefinition;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

@Component
public class RedisLock implements ConcurrentLock {
    private static Logger logger = LoggerFactory.getLogger(RedisLock.class);
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private ThreadLocal<String> threadLocal = new ThreadLocal();
    private static final int EXPIRE = 60;

    public RedisLock() {
    }

    public boolean lock(String key) {
        String now = this.nowTime();
        return this.lock(key, now);
    }

    private boolean lock(String key, String value) {
        if(StringUtils.isEmpty(key)) {
            return false;
        } else {
            RedisConnection connection = null;

            boolean isLock;
            try {
                connection = this.stringRedisTemplate.getConnectionFactory().getConnection();
                isLock = connection.setNX(key.getBytes(), value.getBytes()).booleanValue();
            } catch (Exception var13) {
                throw new BusinessException(ErrorCodeDefinition.REDIS_SETVALUE_ERROR.dynamicMessage("使用redis添加锁失败,key:{0},错误信息：{1}", new Object[]{key, "执行redis lock失败."}), var13);
            } finally {
                if(connection != null && !connection.isClosed()) {
                    try {
                        connection.close();
                    } catch (DataAccessException var12) {
                        logger.warn("redis上锁完成后，关闭连接出错，错误信息：{}", var12.getMessage());
                    }
                }

            }

            return isLock;
        }
    }

    public boolean lock(String key, boolean needExpire) {
        boolean lockSuccess;
        if(needExpire) {
            lockSuccess = this.lock(key, 60);
        } else {
            lockSuccess = this.lock(key);
        }

        return lockSuccess;
    }

    public boolean lock(String key, int expire) {
        String value = this.nowTime();
        return this.lock(key, value, expire);
    }

    public boolean lockCAS(String key, boolean needExpire) {
        String value = this.nowTime();
        boolean lockSuccess;
        if(needExpire) {
            lockSuccess = this.lock(key, value, 60);
        } else {
            lockSuccess = this.lock(key);
        }

        if(lockSuccess) {
            this.threadLocal.set(value);
        }

        return lockSuccess;
    }

    private boolean lock(String key, String value, int expire) {
        String luaScript = "local lockSuccess = redis.call(\'setnx\',KEYS[1],ARGV[1]);local expireSuccess = 0;if lockSuccess == 1 then         expireSuccess = redis.call(\'expire\',KEYS[1],ARGV[2]);        if expireSuccess == 0 then                 redis.call(\'del\',KEYS[1]);                return 0;        else                 return 1;        end else         return 0;end return 0;";
        ArrayList keys = new ArrayList();
        keys.add(key);
        DefaultRedisScript script = new DefaultRedisScript(luaScript, Long.class);
        Long executeResult = (Long)this.stringRedisTemplate.execute(script, keys, new Object[]{value, String.valueOf(expire)});
        return executeResult.longValue() == 1L;
    }

    public boolean unlock(String key) {
        if(StringUtils.isEmpty(key)) {
            return false;
        } else {
            try {
                this.stringRedisTemplate.delete(key);
                return true;
            } catch (Exception var3) {
                throw new BusinessException(ErrorCodeDefinition.REDIS_DELVALUE_ERROR.dynamicMessage("删除redis锁失败,key:{0},错误信息：{1}", new Object[]{key, "执行redis unlock失败"}), var3);
            }
        }
    }

    public boolean unlockCAS(String key) {
        if(StringUtils.isEmpty(key)) {
            return false;
        } else {
            try {
                String e = (String)this.threadLocal.get();
                if(e == null) {
                    return false;
                } else {
                    String luaScript = "local currentValue = redis.call(\'get\',KEYS[1]);if currentValue == ARGV[1] then         redis.call(\'del\',KEYS[1]);        return 1;else         return 0;end return 0;";
                    ArrayList keys = new ArrayList();
                    keys.add(key);
                    DefaultRedisScript script = new DefaultRedisScript(luaScript, Long.class);
                    Long executeResult = (Long)this.stringRedisTemplate.execute(script, keys, new Object[]{e});
                    if(executeResult.longValue() == 1L) {
                        this.threadLocal.remove();
                        return true;
                    } else {
                        return false;
                    }
                }
            } catch (Exception var7) {
                throw new BusinessException(ErrorCodeDefinition.REDIS_DELVALUE_ERROR.dynamicMessage("删除redis锁失败,key:{0},错误信息：{1}", new Object[]{key, "执行redis unlockCAS失败"}), var7);
            }
        }
    }

    private String nowTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String dateNowStr = sdf.format(d);
        BoundValueOperations op = this.stringRedisTemplate.boundValueOps("lock_increment_seq");
        Long seqValue = op.increment(1L);
        if(seqValue.longValue() == 1L) {
            op.expire(24L, TimeUnit.HOURS);
        }

        return dateNowStr.concat("_" + seqValue);
    }
}
