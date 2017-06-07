/*
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: wangchong
 * Date: 2014-1-20
 * Description:ErrorCode.java 
 */
package com.tuniu.car.basic.exception;


/**
 * 错误码
 * @author James
 * 
 */
public class ErrorCode {
	private static int TYPE = 90;

	private int errCode;

    private String errMessage;
    
    public ErrorCode() {
    	
    }
    
    public ErrorCode(int errCode) {
    	super();
    	this.errCode = errCode;
    }

    public ErrorCode(int errCode, String errMessage) {
        super();
        this.errCode = errCode;
        this.errMessage = errMessage;
    }

    /**
     * @return the errCode
     */
    public int getErrCode() {
        return errCode;
    }
    
    public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return errCode + "-" + errMessage;
    }


	public void setConstantValue(String value) {
		if (null != value) {
			errCode = Integer.valueOf(value);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Integer getConstantValue() {
		return errCode;
	}
}
