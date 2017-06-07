/*
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: renyarong
 * Date: 2015年7月25日
 * Description:InheritableBusinessContext.java 
 */
package com.tuniu.car.basic.support;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 线程上下文，全局使用，可以被子线程继承、跟随业务上下文中断以及恢复
 * ADD resumeProductClassIdByOrderId AT 2015-12-10 MAENLIANG
 * @author renyarong
 */
public class InheritableBaseContext {

    protected InheritableBaseContext() {}

    private final static InheritableThreadLocal<Map<String, Object>> BIZ_MAP = new InheritableThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>();
        }
    };

    /**
     * 设置属性
     * @param key
     * @param value
     */
    public static void setObjectProperty(String key, Object value) {
        if (StringUtils.isNotBlank(key)) {
            BIZ_MAP.get().put(key.toUpperCase(), value);
        }
    }

    /**
     * 设置只读属性，如果值已存在，则保留原有值，返回context中的值
     * @param key
     * @param value
     * @return 设置的值
     */
    public static Object setReadOnlyProperty(String key, Object value) {
        Object oldValue = null;
        if (StringUtils.isNotBlank(key) && (oldValue = BIZ_MAP.get().get(key.toUpperCase())) == null) {
            BIZ_MAP.get().put(key.toUpperCase(), value);
            return value;
        }
        return oldValue;
    }
    
    /**
     * 获取变量
     * @param key
     * @return 获取的变量值
     */
    public static Object getObjectProperty(String key) {
        if (StringUtils.isNotBlank(key)) {
            return BIZ_MAP.get().get(key.toUpperCase());
        }
        return null;
    }
    
    public static void clearThreadContextInfo() {
    	BIZ_MAP.remove();
    }
}
