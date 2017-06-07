/*
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: wangchong
 * Date: 2014-1-20
 * Description:ErrorCodeDefinition.java 
 */
package com.tuniu.car.basic.exception;

import java.text.MessageFormat;

import com.tuniu.car.basic.constants.ErrorMessages;
import com.tuniu.car.basic.vo.error.code.SystemErrCodeMapping;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 错误码定义接口</br><strong>BOSS3.0 错误码值范围 1700000~1799999</strong>
 * 
 * 基础公共模块错误码范围1700001~1709999 
 * 状态机模块错误码范围：1710000~1719999
 * 资源模块错误码范围：1720000~1729999 
 * 调用外部系统接口错误码范围：1730000~1739999
 * 对外提供接口错误码范围：1740000~1749999
 * 
 * 
 * 
 */
public class ErrorCodeCreator {
	private static final Logger logger = LoggerFactory.getLogger(ErrorCodeCreator.class);
	
	/**
	 *  外部接口调用参数校验异常
	 */
	private static int INVOKE_OUTINTF_ERROR = 1730003;
	
    /**
     * 设置静态异常错误描述
     * @param errMessage 错误描述字符串：系统异常
     * @return 异常码错误对象
     */
    public ErrorCode setMessage(int errCode, String errMessage) {
        return new ErrorCode(errCode, errMessage);
    }

    /**
     * 动态异常消息设置
     * @param errMessage 消息基础模板，例如：参数验证失败-{0}
     * @param params 替换动态参数数组
     * @return 异常码错误对象
     */
    public static ErrorCode dynamicMessage(int errorCode, String errMessage, Object... params) {
    	Validate.noNullElements(params);
        ErrorCode tempResult = null;
        //从品类对应错误信息表或缓存中获取
        if (errorCode == ErrorCodeDefinition.INVOKE_OUTINTF_ERROR.getErrorCode().getErrCode()) {// ERRORCODE映射适配
        	SystemErrCodeMapping systemErrCodeMapping = new SystemErrCodeMapping();
            if (ErrorMessages.PROXY_ERROR.equals(errMessage) && params.length > 2) {// 错误码映射
            	systemErrCodeMapping.setErrorCode((Integer) params[1]);
            }
            if (ErrorMessages.PROXY_ERROR.equals(errMessage) && params.length > 3) {// 错误码+产品大类映射
            	systemErrCodeMapping.setProductClassId((Integer) params[3]);
            }
            if (ErrorMessages.PROXY_ERROR.equals(errMessage) && params.length > 4) {// 错误码+产品大类+产品子类映射
            	systemErrCodeMapping.setProductChildClassId((Integer) params[4]);
            }
			tempResult = dynamicMappingMessage(systemErrCodeMapping);
			if (tempResult != null&&tempResult.getErrMessage()!=null) {
				return tempResult;
			}
        }
        //从constant_record表或缓存获取
        if (errorCode == ErrorCodeDefinition.INVOKE_OUTINTF_ERROR.getErrorCode().getErrCode()) {// ERRORCODE映射适配
            if (ErrorMessages.PROXY_ERROR.equals(errMessage) && params.length > 2) {// 错误码映射
                tempResult = dynamicCreateOrderMessage((Integer) params[1], (String) params[2]);
            }
        }
        if (tempResult == null) {// 错误码不匹配时采用原来的数据返回,不侵入原来的设计
            String formatMessage = MessageFormat.format(errMessage, params);
            tempResult = new ErrorCode(errorCode, formatMessage);
        }
        return tempResult;
    }
    
    /**
     * 生成TOF错误码和错误信息
     * @param systemErrCodeMapping 外系统错误码
     * @return
     */
    public static ErrorCode dynamicMappingMessage(SystemErrCodeMapping systemErrCodeMapping) {
    	ErrorCode resultCode = null;
        if (systemErrCodeMapping != null && systemErrCodeMapping.getErrorCode()!=null) {
            /*String cachedErrMsg = ErrorCodeMapper.getMappingErrorCode(systemErrCodeMapping);
            if(cachedErrMsg!=null){
            	resultCode = new ErrorCode(systemErrCodeMapping.getErrorCode(), cachedErrMsg);
            }*/
        }
        return resultCode;
    }

    /**
     * 生成PGA错误码,通过BOSS,NGBOSS下单时外系统错误码Mapping获取PGA错误码
     * @param externalErrorCode 外系统错误码
     * @param externalErrMsg 外系统错误信息
     * @return
     */
    public static ErrorCode dynamicCreateOrderMessage(Integer externalErrorCode, String externalErrMsg) {
        ErrorCode resultCode = null;
       /* if (externalErrorCode != null && externalErrorCode > 0) {
            int pgaErrorCode = ErrorCodeMapper.getPgaErrorCode(externalErrorCode);
            resultCode = new ErrorCode(pgaErrorCode, externalErrMsg);
        }*/
        return resultCode;
    }
}
