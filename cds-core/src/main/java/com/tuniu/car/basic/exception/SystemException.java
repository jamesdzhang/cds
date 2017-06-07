/*
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: wangchong
 * Date: 2014-1-20
 * Description:SystemException.java 
 */
package com.tuniu.car.basic.exception;

/**
 * 系统异常
 * @author James
 * 
 */
public class SystemException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private ErrorCode errorCode;

    public SystemException(ErrorCode errorCode) {
        super(errorCode.toString());
        this.errorCode = errorCode;
    }

    public SystemException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.toString(), cause);
        this.errorCode = errorCode;
    }

    public SystemException(ErrorCodeDefinition definition) {
        this(definition.getErrorCode());
        this.errorCode = definition.getErrorCode();
    }

    public SystemException(ErrorCodeDefinition definition, Throwable cause) {
        this(definition.getErrorCode(), cause);
        this.errorCode = definition.getErrorCode();
    }

    /**
     * @return the errorCode
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
