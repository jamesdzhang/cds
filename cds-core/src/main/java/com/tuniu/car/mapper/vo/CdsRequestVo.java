package com.tuniu.car.mapper.vo;

import com.tuniu.car.basic.mongo.db.CarOrderRequest;
import com.tuniu.car.basic.mongo.db.CarOrderResponse;

import java.util.Date;

/**
 * Created by zhangyaping on 2017/6/5.
 */
public class CdsRequestVo {

    private Integer id;
    private String orderId;
    private String subOrderId;
    private String reqSeq;
    private CarOrderRequest reqPacket;
    private CarOrderResponse respPacket;
    private CarOrderRequest reqPacketCoc;
    private String respPacketCoc;
    private String reqType;
    private Long reqInterval;
    private Integer isNormal;
    private Date addTime;
    private Date updateTime;

    public String getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(String subOrderId) {
        this.subOrderId = subOrderId;
    }

    public CarOrderRequest getReqPacket() {
        return reqPacket;
    }

    public void setReqPacket(CarOrderRequest reqPacket) {
        this.reqPacket = reqPacket;
    }

    public CarOrderRequest getReqPacketCoc() {
        return reqPacketCoc;
    }

    public void setReqPacketCoc(CarOrderRequest reqPacketCoc) {
        this.reqPacketCoc = reqPacketCoc;
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

    public String getReqSeq() {
        return reqSeq;
    }

    public void setReqSeq(String reqSeq) {
        this.reqSeq = reqSeq;
    }

    public CarOrderResponse getRespPacket() {
        return respPacket;
    }

    public void setRespPacket(CarOrderResponse respPacket) {
        this.respPacket = respPacket;
    }

    public String getRespPacketCoc() {
        return respPacketCoc;
    }

    public void setRespPacketCoc(String respPacketCoc) {
        this.respPacketCoc = respPacketCoc;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public Long getReqInterval() {
        return reqInterval;
    }

    public void setReqInterval(Long reqInterval) {
        this.reqInterval = reqInterval;
    }

    public Integer getNormal() {
        return isNormal;
    }

    public void setNormal(Integer normal) {
        isNormal = normal;
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
