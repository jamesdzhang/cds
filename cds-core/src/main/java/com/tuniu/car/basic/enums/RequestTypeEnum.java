
package com.tuniu.car.basic.enums;

import com.tuniu.car.basic.constants.OrderState;

/**
 * 资源类型
 * 
 * @author James
 */
public enum RequestTypeEnum {

    /** 未知 */
    UNKNOW("_","未知", OrderState.UNKNOWN),
    /** 预定 */
    BOOK("create_order","预定", OrderState.UNKNOWN),
    /** 取消 */
    CANCEL("cancel_order","取消订单", OrderState.LOSS_CHECKED),
    /** 核损查询 */
    LOSS_CHECK("check_order_loss","查询核损信息", OrderState.CONFIRMED),
    /** 状态查询 */
    STATUS_QUERY("query_order_status","查询订单状态", OrderState.UNKNOWN),
    /** 司导信息 */
    FEEDBACK_DRIVER_INFO("push_driver_info","司导信息", OrderState.CONFIRMED),
    /** 预定结果反馈 */
    FEEDBACK_CONFIRM_RESULT("feedback_confirm","预定结果反馈", OrderState.CONFIRMED)
    ;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupportStatus() {
        return supportStatus;
    }

    public void setSupportStatus(String status) {
        this.supportStatus = status;
    }

    private String code;

    private String name;

    private String supportStatus;

    RequestTypeEnum(String code, String name,String status){
        this.name = name;
        this.code = code;
        this.supportStatus = status;
    }


    public static RequestTypeEnum codeOf(String code){
        for(RequestTypeEnum tmp : RequestTypeEnum.values()){
            if(tmp.getCode().equals(code))
                return tmp;
        }
        return UNKNOW;
    }

}
