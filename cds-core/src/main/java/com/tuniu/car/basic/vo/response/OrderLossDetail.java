package com.tuniu.car.basic.vo.response;

/**
 * Created by zhangyaping on 2017/5/19.
 */
public class OrderLossDetail {

    private String orderId;
    private String statusName;
    private String statusCode;
    private String lossSchemeId;
    private LossDetail lossDetail;

    public String getLossSchemeId() {
        return lossSchemeId;
    }

    public void setLossSchemeId(String lossSchemeId) {
        this.lossSchemeId = lossSchemeId;
    }

    public LossDetail getLossDetail() {
        return lossDetail;
    }

    public void setLossDetail(LossDetail lossDetail) {
        this.lossDetail = lossDetail;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
