
package com.tuniu.car.basic.constants;


public class OrderState {
    public static String UNKNOWN = "status_unknown";
    public static String CREATED = "order_created";
    public static String CONFIRMING = "order_confirming";
    public static String CONFIRMED = "order_confirmed";
    public static String LOSS_CHECKING = "order_loss_checking";
    public static String LOSS_CHECKED = "order_loss_checked";
    public static String DONE = "order_completed";
    public static String CANCELED = "order_canceled";

    private String stateCode;
    private String cocCode;
    private String stateDesc;
    private String statusBefore;

    public OrderState() {
    }

    public OrderState(String code,String cocCode,String statusBefore, String desc) {
        this.stateCode = code;
        this.stateDesc = desc;
        this.cocCode = cocCode;
        this.statusBefore = statusBefore;
    }

    public String getStatusBefore() {
        return statusBefore;
    }

    public void setStatusBefore(String statusBefore) {
        this.statusBefore = statusBefore;
    }

    public String getCocCode() {
        return cocCode;
    }

    public void setCocCode(String cocCode) {
        this.cocCode = cocCode;
    }

    public String getStateCode() {
        return this.stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateDesc() {
        return this.stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }
}
