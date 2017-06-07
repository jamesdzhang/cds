package com.tuniu.car.core.redis.service;

import javax.validation.constraints.NotNull;

/**
 * From Coc
 */
public interface ConcurrentLock {

    /**
     * 获得锁
     * @param key
     * @return 获得锁返回true，资源已经被锁，无法获取返回false
     */
    boolean lock(@NotNull String key);

    /**
     * 获得锁，是否设置默认的超时时间
     * @param key
     * @return 获得锁返回true，资源已经被锁，无法获取返回false
     */
    boolean lock(@NotNull String key, boolean expire);

    /**
     * 获得锁，设置超时时间，单位秒
     * @param key
     * @return 获得锁返回true，资源已经被锁，无法获取返回false
     */
    boolean lock(@NotNull String key, int expire);

    /**
     * 释放锁
     * @param key
     * @return 释放成功返回true
     */
    boolean unlock(@NotNull String key);
}
