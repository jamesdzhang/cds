/*
 * Copyright (C) 2006-2012 Tuniu All rights reserved
 * Author: wangchong
 * Date: 2014-1-20
 * Description:ErrorCodeDefinition.java 
 */
package com.tuniu.car.basic.exception;


import com.tuniu.car.basic.vo.error.code.SystemErrCodeMapping;

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
public enum ErrorCodeDefinition {

    DEFAULT_SUCCESS(new ErrorCode(1700000)),//调用默认值
    DEFAULT_ERROR(new ErrorCode(170500,"系统开小差了，请稍后重试")),//调用默认值
    PARAM_PARSE_ERROR(new ErrorCode(1701500,"非法参数，请检查是否使用正确的请求类型")),//参数转换失败
    ORDER_STATE_NOT_SUPPORT(new ErrorCode(1702500,"当前订单状态不支持该请求")),//订单状态不支持
    ORDER_DUPLICATE_REQUEST(new ErrorCode(1703500,"重复请求")),//订单状态不支持
    SIT_MOCK_PAYMENT_FAILED(new ErrorCode(1701000,"【TEST】订单已生成{0}，模拟支付失败，无法自行发起预定")),//订单状态不支持

    /************ 基础公共模块错误码范围1700001~1709999 *********************/
    SYSTEM_ERROR(new ErrorCode(1700001)),//系统异常
    OTHER_SYSTEM_ERROR(new ErrorCode(1700002)), // 其他系统异常
    INVOKE_DB_ERROR(new ErrorCode(1700003)), // 调用xx系统数据库异常
    ORD_CONVERT_ERROR(new ErrorCode(1700004)), // 字符串字符转码异常
    ORD_COPY_ERROR(new ErrorCode(1700005)), // 参数数据拷贝异常
    CREATE_ORDER_VALIDATE(new ErrorCode(1700006)), // 下单数据校验失败
    CONTEXT_SYS_ERROR(new ErrorCode(1700007)), // 接口防重复系统异常
    ABNORMAL_OPERATION(new ErrorCode(1700008)), // 用户异常操作
    NORIGHT_TO_RESIGN_ORDER(new ErrorCode(170009)),//无转单权限异常
    OPEN_PAY_SWITCH_ERROR(new ErrorCode(170010)),//支付开发打开异常
    PAY_ORDER_MONEY_ERROR(new ErrorCode(170011)),//支付异常
    MUTI_CFM_SEQID_ERROR(new ErrorCode(170012)),//确认管理seqId已存在异常
    DOUBLE_BOOKING_ERROR(new ErrorCode(170013,"重复下单")),//重复下单
    DOUBLE_BOOKING_SHUTTER_CAR_ERROR(new ErrorCode(170014,"重复下单")),
    INTL_SHUTTER_CAR_EMAIL_ERROR(new ErrorCode(170015)),//国际接送机邮件发送参数异常
    PROCDUCT_NO_MANAGER(new ErrorCode(170016)),//产品线路 产品专员配置错误
    PROCDUCT_NO_START_CITY(new ErrorCode(170017)),//产品线路 为配置出发城市

    /************ 调用外部系统接口错误码范围：1730000~1739999 ****************************/
    INVOKE_SYS_ERROR(new ErrorCode(1730000)), // 调用xx系统xx接口异常
    INTF_ANALYZE_ERROR(new ErrorCode(1730001)), // PGA接口参数解析异常
    ORD_CREATE_FAILED(new ErrorCode(1730002)), // PGA订单下单失败 add by fengxuekui at
    INVOKE_OUTINTF_ERROR(new ErrorCode(1730003)), // 外部接口调用参数校验异常
    CAR_RESOURCE_INTF_ANALYZE_ERROR(new ErrorCode(1730004)), // 外部接口调用参数校验异常
    INVOKE_TIMEOUT_ERROR(new ErrorCode(1730005)), // 外部接口调用超时
    INVOKE_RETURN_NULL_ERROR(new ErrorCode(1730006)), // 外部接口调用返回为空


    /************ 对外提供接口错误码范围：1740000~1749999 ****************************/
    ORD_BASEVALID_ERROR(new ErrorCode(1740000)), // 对外提供的接口参数基础校验异常（空值、长度、类型）
    ORD_BUSINESSVALID_ERROR(new ErrorCode(1740001)), // 对外提供的接口逻辑校验异常（例如：出游时间必须大于当前系统时间）
    ORD_CREATE_PROMOTION_ERROR(new ErrorCode(1740002)),// 促销失败（例如：出游时间必须大于当前系统时间）
    ORD_JD_CLOSE_PAY_SWITCH_ERROR(new ErrorCode(1740003)),// 京东订单关闭支付开关失败（未到清位时间，不允许关闭支付开关）
    ORD_CREATE_ORDER_ERROR(new ErrorCode(1740004)),//外系统调用下单接口，下单失败异常
    ORD_QUERY_SALER_NOTFOUND_ERROR(new ErrorCode(1740005)),//pga查询交接客服未查到异常


    /************ Redis操作错误码范围：1750000~1759999 ****************************/
	REDIS_OPERATE_ERROR(new ErrorCode(1750000)),//操作redis出错
    REDIS_SETVALUE_ERROR(new ErrorCode(1750001)),//向redis放值错误
    REDIS_DELVALUE_ERROR(new ErrorCode(1750002)),//redis删值错误
    REDIS_SET_EXPIRE_ERROR(new ErrorCode(1750003))//redis删值错误
    ;

	
    /**
     * 错误码实例
     */
    private ErrorCode errorCode;

    private ErrorCodeDefinition(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 设置静态异常错误描述
     * @param errMessage 错误描述字符串：系统异常
     * @return 异常码错误对象
     */
    public ErrorCode setMessage(String errMessage) {
        return new ErrorCode(errorCode.getErrCode(), errMessage);
    }

    /**
     * 动态异常消息设置
     * @param errMessage 消息基础模板，例如：参数验证失败-{0}
     * @param params 替换动态参数数组
     * @return 异常码错误对象
     */
    public ErrorCode dynamicMessage(String errMessage, Object... params) {
        return ErrorCodeCreator.dynamicMessage(errorCode.getErrCode(), errMessage, params);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    /**
     * 生成TOF错误码和错误信息
     * @param systemErrCodeMapping 外系统错误码
     * @return
     */
    @Deprecated
    public static ErrorCode dynamicMappingMessage(SystemErrCodeMapping systemErrCodeMapping) {
        return ErrorCodeCreator.dynamicMappingMessage(systemErrCodeMapping);
    }
    /**
     * 生成PGA错误码,通过BOSS,NGBOSS下单时外系统错误码Mapping获取PGA错误码
     * @param externalErrorCode 外系统错误码
     * @param externalErrMsg 外系统错误信息
     * @return
     */
    @Deprecated
    public static ErrorCode dynamicCreateOrderMessage(Integer externalErrorCode, String externalErrMsg) {
        return ErrorCodeCreator.dynamicCreateOrderMessage(externalErrorCode, externalErrMsg);
    }

    public static ErrorCode find(ErrorCode errorCode){
        for(ErrorCodeDefinition tmp : ErrorCodeDefinition.values()){
            if(tmp.getErrorCode().getErrCode() == errorCode.getErrCode())
                return tmp.getErrorCode();
        }
        return DEFAULT_ERROR.getErrorCode();
    }

    public static ErrorCode find(int errorCode){
        for(ErrorCodeDefinition tmp : ErrorCodeDefinition.values()){
            if(tmp.getErrorCode().getErrCode() == errorCode)
                return tmp.getErrorCode();
        }
        return DEFAULT_ERROR.getErrorCode();
    }
}
