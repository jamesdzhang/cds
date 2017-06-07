/*
 * Copyright (C) 2006-2013 Tuniu All rights reserved
 * Author: gaoyan
 * Date: 2013-01-08
 * Description: 请求返回的公用数据
 */
package com.tuniu.car.basic.vo.response;

import com.tuniu.car.basic.mongo.db.SeqPacket;

/**
 * 用于接入mongo
 * @author James
 */

public class ResponseVo implements SeqPacket{
    // 成功标记
    private boolean success;
    // 提示信息
    private String msg;
    // 错误码
    private int errorCode;
    // 返回的具体数据
    private Object data;

    /**
     * @return success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success
     *            :
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg
     *            :
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     *            :
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data
     *            :
     */
    public void setData(Object data) {
        this.data = data;
    }

}
