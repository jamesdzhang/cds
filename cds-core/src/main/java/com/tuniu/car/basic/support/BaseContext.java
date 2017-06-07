/*
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: wangchong
 * Date: 2014-1-16
 * Description:BusinessContext.java 
 */
package com.tuniu.car.basic.support;

import com.tuniu.car.basic.constants.Constants;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import java.util.HashMap;
import java.util.Map;


/**
 * 线程上下文
 * @author James
 * 
 */
public class BaseContext {
    /**
     * 线程变量
     */
    private static final ThreadLocal<Map<String, String>> THREAD_LOCAL = new ThreadLocal<Map<String, String>>();

    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL_OBJECT = new ThreadLocal<Map<String, Object>>();

    /**
     * 获取变量
     * @param key
     * @return 获取的变量值
     */
    public static String getProperty(String key) {
        Validate.notEmpty(key);
        String result = null;
        Map<String, String> threadMap = THREAD_LOCAL.get();
        if (null != threadMap) {
            result = threadMap.get(key.toUpperCase());
        }
        if (StringUtils.isEmpty(result)) {
            result = String.valueOf(InheritableBaseContext.getObjectProperty(key));
        }
        return result;
    }

    /**
     * 设置属性
     * @param key
     * @param value
     */
    public static void setProperty(String key, String value) {
        Validate.notEmpty(key);
        Validate.notEmpty(value);
        Map<String, String> threadMap = THREAD_LOCAL.get();
        if (null == threadMap) {
            threadMap = new HashMap<String, String>();
            THREAD_LOCAL.set(threadMap);
        }
        threadMap.put(key.toUpperCase(), value);
        InheritableBaseContext.setObjectProperty(key, value);
    }

    /**
     * 设置属性
     * @param key
     * @param value
     */
    public static void setObjectProperty(String key, Object value) {
        if (StringUtils.isNotBlank(key)) {
            Map<String, Object> threadMap = THREAD_LOCAL_OBJECT.get();
            if (null == threadMap) {
                threadMap = new HashMap<String, Object>();
                THREAD_LOCAL_OBJECT.set(threadMap);
            }
            threadMap.put(key.toUpperCase(), value);
            InheritableBaseContext.setObjectProperty(key,value);
        }
    }

    /**
     * 获取变量
     * @param key
     * @return 获取的变量值
     */
    public static Object getObjectProperty(String key) {
        Object result = null;
        if (StringUtils.isNotBlank(key)) {
            Map<String, Object> threadMap = THREAD_LOCAL_OBJECT.get();
            if (null != threadMap) {
                result = threadMap.get(key.toUpperCase());
            }
            if (result == null) {
                result = InheritableBaseContext.getObjectProperty(key);
            }
        }
        return result;
    }

    /**
     * 获取当前操作人id
     * @return
     */
    public static void setCurrentUid(Object uid) {
        if (uid != null) {
            BaseContext.setObjectProperty(Constants.CURRENT_UID_KEY, uid);
        }
    }

    /**
     * 设置当前操作人姓名
     * @return
     */
    public static void setCurrentUserName(Object uName) {
        if (uName != null) {
            BaseContext.setObjectProperty(Constants.CURRENT_SALER_NAME_KEY, uName);
        }
    }
    
    public static void clearThreadContextInfo() {
        THREAD_LOCAL.remove();
        THREAD_LOCAL_OBJECT.remove();
    }
}
