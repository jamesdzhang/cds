/*
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: wangchong
 * Date: 2014-1-20
 * Description:BusinessException.java 
 */
package com.tuniu.car.basic.exception;

import com.tuniu.car.utils.StringUtils;

/**
 * 业务异常
 * @author James
 * 
 */
public class BusinessException extends RuntimeException {

    /**
     * 序列化使用
     */
    private static final long serialVersionUID = 1L;

    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode,String... args) {
        super(args!=null? StringUtils.msgFormat(errorCode.toString(),args):errorCode.toString());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.toString(), cause);
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCodeDefinition definition) {
        this(definition.getErrorCode());
        this.errorCode = definition.getErrorCode();
    }

    public BusinessException(ErrorCodeDefinition definition, Throwable cause) {
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
