/*
 * Copyright (C) 2006-2013 Tuniu All rights reserved
 * Author: gaoyan
 * Date: 2013-01-08
 * Description: 请求返回的公用数据
 */
package com.tuniu.car.basic.vo.response;

/**
 * 此类描述的是：请求返回的公用数据
 * 
 * @author James
 */

public class ResponseDto {
    // 成功标记
    private boolean success;
    // 提示信息
    private String msg;
    // 错误码
    private int errorCode;
    //请求公司码
    private String requestSource;
    // 返回的具体数据
    private Object responseBody;

    public ResponseDto(){
    }

    public ResponseDto(boolean isSuccess,String msg, int errorCode){
        this.success = isSuccess;
        this.msg = msg;
        this.errorCode = errorCode;
    }

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

    public String getRequestSource() {
        return requestSource;
    }

    public void setRequestSource(String requestSource) {
        this.requestSource = requestSource;
    }

    public Object getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }
}
