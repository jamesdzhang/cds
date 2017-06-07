package com.tuniu.car.basic.interceptor;


import com.tuniu.car.basic.interceptor.context.InvokeContext;

/**
 * 任务处理接口
 * @author James
 * 
 */
public interface FilterAction {
    /**
     * 任务调用
     * @param invokeContext 调用上下文
     * @throws Exception 任务执行异常
     */
    void invoke(InvokeContext invokeContext) throws Exception;
}
