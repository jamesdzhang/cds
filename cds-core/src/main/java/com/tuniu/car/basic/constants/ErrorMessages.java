package com.tuniu.car.basic.constants;

/**
 * 用于定义错误信息常量
 * @author tob
 *
 */
public interface ErrorMessages {
	/** UnsupportedEncodingException异常信息 */
    String BOSS_MSG_ENCODING = "Boss系统下单响应消息UTF-8编码失败。";
    
	/** 框架异常 */
    String COMMON_PLATFORM_ERROR = "框架异常：{0}";
    
    /** 参数校验异常信息 */
    String COMMON_PARAMETER_ERROR = "参数错误：{0}";
    
	/** TOJSONSTRING_SYS_ERROR转换异常 */
    String TOJSONSTRING_SYS_ERROR = "转换到json格式字符串失败。";
    
	/** httpclient 调用异常 */
    String HTTPCLIENT_SYS_ERROR = "查询外部系统异常，请联系技术支持!外部接口系统异常：系统:\"{0}\"的接口:\"{1}\"调用失败。";
    
	/** PROXY异常 */
    String PROXY_ERROR ="调用外系统异常，请联系技术支持！{0}调用PROXY异常,ExternalErrorCode:{1},ExternalErrorMsg:{2}.";
    
    /** PROXY异常  调用接口超时 */
    String PROXY_TIMEOUT_ERROR ="系统繁忙，请休息片刻，稍后重试";
    
    /** beanutil dtomapping 复制dto字段异常 */
    String DTOMAPPING_SYS_ERROR = "DTOMapping拷贝系统异常。";
    
    /** beanutil copyproperties复制bean字段异常 */
    String COPYPROPERTIES_SYS_ERROR = "COPYPROPERTIES拷贝系统异常。异常信息: {0}";
    
    /** 异常信息：{0}   */
    String COMMON_EXCEPTION_MSG = "DTOMapping 字段转换异常信息：{0}";
    
    String REDIS_LOCK_SET_ERROR = "使用redis添加锁失败,key:{0},错误信息：{1}";

    String REDIS_LOCK_REMOVE_ERROR = "删除redis锁失败,key:{0},错误信息：{1}";

    String REDIS_SET_EXPIRE_ERROR = "设置redis键的有效期时间失败,key:{0},错误信息：{1}";
}
