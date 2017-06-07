package com.tuniu.car.basic.constants;

public interface Constants {

    /**
     * 默认系统uid
     */
    Integer DEFAULT_SYSTEM_UID = 39;

    /**
     * 默认系统用户名称
     */
    String DEFAULT_SYSTEM_UNAME = "系统";

    /**
     * uid
     */
    String CURRENT_UID_KEY = "CURRENT_UID";

    /**
     * 客服名称
     */
    String CURRENT_SALER_NAME_KEY = "CURRENT_SALER_NAME";

    /**
     * rest 请求方法 GET
     */
    String REST_METHOD_GET = "GET";

    /**
     * rest 请求方法 POST
     */
    String REST_METHOD_POST = "POST";

    /**
     * rest 请求方法 PUT
     */
    String REST_METHOD_PUT = "PUT";

    /**
     * rest 请求方法 DELETE
     */
    String REST_METHOD_DELETE = "DELETE";

    /**
     * 响应状态码 OK/ERR/Repeat
     */
    String TN_STATUS = "TN-Status";

    /**
     * 请求来源
     */
    String TN_SOURCE = "TN-Source";

    /**
     * 目的
     */
    String TN_TARGET = "TN-Target";

    /**
     * 请求流水号
     */
    String TN_SEQNO = "TN-SeqNo";

    /**
     * 接口调用地址
     */
    String TN_REMOTE_ADDR = "TN-RemoteAddr";

    /**
     * serverIp
     */
    String TN_SERVER_ADDR = "TN-ServerAddr";

    /**
     * serverPort
     */
    String TN_SERVER_PORT = "TN-ServerPort";

    /**
     * serverRunTime
     */
    String TN_SERVER_RUN_TIME = "TN-ServerRunTime";

    /**
     * serverThreadCount
     */
    String TN_SERVER_THREADCOUNT = "TN-ServerThreadCount";

    /**
     * 接口调用地址
     */
    String SQL_SERVICE_SOURCE = "SqlSourceService";

    /**
     * 接口以及衍生接口耗时跟踪统计标志，当值为fullStack时忽略 LogPersistenceServiceFactory 中日志分级策略,
     * 此标志存放于ThreadLocal中，被LogPersistenceServiceFactory中的isPolicyMatched方法调用
     */
    String SERVICE_TIMECOST_FULL_STACK_TRACE_MONITOR_STATEGY="serviceTimeCostFullStackMonitorStategy";

    /**
     * 接口以及衍生接口耗时跟踪统计內容處理标志，当ServiceTimeCostMonitorRegister.MonitorStategy为fullStack时忽略 LogPersistenceServiceFactory 中日志分级策略
     * 此标志被放于ThreadLocal中,日志保存时调用CLEAR_CONTENT时清空InvokeLog的content字段
     */
    String SERVICE_TIMECOST_FULL_STACK_TRACE_LOG_PROCESS_STATEGY="serviceTimeCostFullStackTraceLogProcessStategy";

    /**
     * 调用类型 0请求 1响应
     */
    int INVOKE_TYPE_REQUEST = 0;

    /**
     * 调用类型 0请求 1响应
     */
    int INVOKE_TYPE_RESPONSE = 1;

    /**
     * 调用类型 2 sql 统计
     */
    int INVOKE_TYPE_SQL = 2;

    /**
     * 调用类型 3 接口耗时统计
     */
    int INVOKE_TYPE_MONITOR = 3;

    /**
     * 全局orderId
     */
    String INVOKE_LOG_PARAM_ORDER_ID = "TN-invokeLogOrderId";

    /**
     * 响应状态码-成功
     */
    String TN_STATUS_OK = "OK";

    /**
     * 响应状态码-请求失效
     */
    String TN_STATUS_ERR = "ERR";

    /**
     * 响应状态码-重试
     */
    String TN_STATUS_REPEAT = "Repeat";

    /**
     * 字符编码
     */
    String CHARSET = "UTF-8";

    /**
     * 系统应用名
     */
    String APPLICATON_NAME = "BOSS3";

    /**
     * PGA系统应用名
     */
    String PGA_APPLICATON_NAME = "PGA";

    /**
     * ngBoss系统应用名
     */
    String NG_APPLICATON_NAME = "NG";

    /**
     * 老Boss系统应用名
     */
    String BOSS_APPLICATON_NAME = "BOSS";

    /**
     * 分单系统应用名
     */
    String SRH_APPLICATON_NAME = "SRH";

    /**
     * 资源系统应用名
     */
    String RES_APPLICATON_NAME = "RES";

    /**
     * 财务系统名
     **/
    String FMIS_APPLICATION_NAME = "FMIS";

    /**
     * 支付系统名
     **/
    String UGV_APPLICATION_NAME = "UGV";

    /**
     * 适配系统名
     **/
    String FIT_APPLICATION_NAME = "FIT";

    /**
     * 产品系统名
     **/
    String PRD_APPLICATION_NAME = "PRD";

    /**
     * Fab系统名
     **/
    String CRM_APPLICATION_NAME = "CRM";

    /**
     * 短信系统名
     **/
    String SMS_APPLICATION_NAME = "SMS";

    /**
     * 出团通知系统名
     **/
    String DPN_APPLICATION_NAME = "DPN";

    /**
     * 底层系统三字码 RTX
     */
    String PLA_APPLICATION_NAME = "PLA";

    /**
     * 确认管理系统名称
     */
    String CFM_APPLICATION_NAME = "CFM";

    /**
     * 邮件系统名
     */
    String EDM_APPLICATION_NAME = "EDM";

    /**
     * 传真系统名
     */
    String FAX_APPLICATION_NAME = "FAX";

    /**
     * 投诉质检系统名
     */
    String CMP_APPLICATION_NAME = "CMP";

    /**
     * OA系统名
     */
    String OA_APPLICATION_NAME = "OA";

    /**
     * TBS系统名
     */
    String TBS_APPLICATION_NAME = "TBS";

    /**
     * 网站系统名
     */
    String HSWW_APPLICATION_NAME = "HSWW";
    
    /**
     * 酒店系统名
     */
    String HDS_APPLICATION_NAME = "HDS";

    /**
     * 保险系统名
     */
    String ADAPTER_APPLICATION_NAME = "ADAPTER";

    /**
     * 旅游券系统名
     */
    String PMF_APPLICATION_NAME = "PMF";

    /**
     * 抵用券系统名
     */
    String OLV_APPLICATION_NAME = "OLV";

    /**
     * 促销系统名
     */
    String LUMOS_APPLICATION_NAME = "LUMOS";
    
    /** 新结算平台 */
    String SCS_APPLICATION_NAME = "SCS";
    
    String RIS_APPLICATION_NAME = "RIS";
    
    String SNC_APPLICATION_NAME = "SNC";
    
    String NEB_APPLICATION_NAME = "NEB";

    /**
     * 在线预定
     */
    String ONLINE_ORDER = "3";

    /**
     * 状态机获取锁失败时的重试次数
     */
    int STM_LOCK_RETRY = 3;

    /**
     * redis默认超时时间 单位是秒
     */
    int REDIS_DEFAULT_TIMEOUT = 10 * 60 * 60;// 10小时

    /**
     * boss3订单系统短信系统配置的系统id
     */
    int BOSS3_SMS_SYSTEM_ID = 63;

    /**
     * Boss3系统id
     */
    int systemId = 71;

    String SESSION_ORDER_ID = "SESSION_ORDER_ID";

    /**
     * 发起 询占确 请求调用失败
     */
    int REQUEST_INQUIRYOCCUPYCONFIRM_FAILED = 0;

    /**
     * 发起询占确请求重复
     */
    int REQUEST_INQUIRYOCCUPYCONFIRM_REPET= -1;

    /**
     * 无确认管理 SeqId时，定义为0
     */
    int CONFIRM_OCCUPY_INQUIRY_NOSEQID = 0;

    /**
     * 产品品类ID<br>
     * 设置业务上下文，用于区分业务分支，added by renyarong, 2015.7.24
     */
    final static String PRODUCT_CLASS_ID = "PRODUCT_CLASS_ID";
    /**
     * 产品品类ID<br>
     * 设置业务上下文，用于区分业务分支，added by renyarong, 2015.7.24
     */
    final static String PRODUCT_CHILD_CLASS_ID = "PRODUCT_CHILDCLASS_ID";
    /**
     * 出游人分隔符
     */
    String TOURIST_APPEND_FLAG = ",";

    /**
     * 全部取消占位
     */
    Integer ALL_RELEASE = 1;

    /**
     * 部分取消占位
     */
    Integer PART_RELEASE = 2;

    /**
     * 系统应用名
     */
    String STM_APPLICATON_NAME = "STM";
    
    /**
     * 用车订单
     */
    public static final Integer CAR_PRODUCT_ID = 210000877;
    
    /**
     * 用车分单taskId
     */
    public static final Integer CAR_TASK_ID = 64;
    
    /**
     * 马尔代夫（一级目的地分组）
     */
    public static final Integer  MALDIVES= 31;
    
    /**
     * 散客酒店 用于显示行程下的资源
     * key
     */
    Integer INDIVIDUAL_HOTEL_KEY = -2;
    
    /**
     * 散客酒店  用于显示行程下的资源
     * value
     */
    String INDIVIDUAL_HOTEL_VALUE = "散客酒店";

    /**
     * 中国国家编号
     */
    public static final Integer COUNTRY_CHINA_ID = 40;

    /**
     * 中国国际区号
     */
    public static final String COUNTRY_CHINA_INTL_CODE = "0086";
    
    
    /**
     * 查询权限系统,订单详情页资源编号
     */
    public static final String TOF_RES_ORD_DETAIL = "TOF_RES_ORD_DETAIL";
}
