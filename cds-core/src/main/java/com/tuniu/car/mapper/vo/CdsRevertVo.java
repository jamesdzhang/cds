package com.tuniu.car.mapper.vo;

import com.tuniu.car.basic.mongo.db.CarOrderRequest;
import com.tuniu.car.basic.mongo.db.CarOrderResponse;

import java.util.Date;

/**
 * Created by zhangyaping on 2017/6/5.
 */
public class CdsRevertVo {

    private Integer id;
    private String orderId;
    private String subOrderId;
    private String revertType;
    private CarOrderRequest<?> reqPacket;
    private CarOrderResponse<?> respPacket;
    private Integer retryTimes;
    private Date addTime;
    private Date updateTime;
    private Integer isSuccess;

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(String subOrderId) {
        this.subOrderId = subOrderId;
    }

    public String getRevertType() {
        return revertType;
    }

    public void setRevertType(String revertType) {
        this.revertType = revertType;
    }

    public CarOrderRequest<?> getReqPacket() {
        return reqPacket;
    }

    public void setReqPacket(CarOrderRequest<?> reqPacket) {
        this.reqPacket = reqPacket;
    }

    public CarOrderResponse<?> getRespPacket() {
        return respPacket;
    }

    public void setRespPacket(CarOrderResponse<?> respPacket) {
        this.respPacket = respPacket;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
