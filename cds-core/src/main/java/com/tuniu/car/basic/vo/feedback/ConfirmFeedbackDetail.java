package com.tuniu.car.basic.vo.feedback;

import com.tuniu.car.basic.vo.request.RequestDetail;

/**
 * Created by zhangyaping on 2017/5/18.
 */
public class ConfirmFeedbackDetail implements RequestDetail{

    private String orderId;
    private String msg;
    private Boolean confirmResult;
    private String certNum;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getConfirmResult() {
        return confirmResult;
    }

    public void setConfirmResult(Boolean confirmResult) {
        this.confirmResult = confirmResult;
    }

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }
}
