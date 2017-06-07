package com.tuniu.car.basic.vo.forward.coc.create.returned;

import java.util.Date;

/**
 * From COC
 */
public class StmOrdStatus {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer flowId;
    private Integer orderId;
    private String statusCode;
    private String statusName;
    private String lastStatusCode;
    private String lastStatusName;
    private String logicCode;
    private Integer runState;
    private String enterSignAfterChangeStatus;
    private Integer addId;
    private Date addTime;
    private Integer updateId;
    private Date updateTime;
    private Integer delFlag;

    public StmOrdStatus() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFlowId() {
        return this.flowId;
    }

    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
    }

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode == null?null:statusCode.trim();
    }

    public String getStatusName() {
        return this.statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName == null?null:statusName.trim();
    }

    public String getLastStatusCode() {
        return this.lastStatusCode;
    }

    public void setLastStatusCode(String lastStatusCode) {
        this.lastStatusCode = lastStatusCode == null?null:lastStatusCode.trim();
    }

    public String getLastStatusName() {
        return this.lastStatusName;
    }

    public void setLastStatusName(String lastStatusName) {
        this.lastStatusName = lastStatusName == null?null:lastStatusName.trim();
    }

    public String getLogicCode() {
        return this.logicCode;
    }

    public void setLogicCode(String logicCode) {
        this.logicCode = logicCode == null?null:logicCode.trim();
    }

    public Integer getAddId() {
        return this.addId;
    }

    public void setAddId(Integer addId) {
        this.addId = addId;
    }

    public Date getAddTime() {
        return this.addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getUpdateId() {
        return this.updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getRunState() {
        return this.runState;
    }

    public void setRunState(Integer runState) {
        this.runState = runState;
    }

    public String getEnterSignAfterChangeStatus() {
        return this.enterSignAfterChangeStatus;
    }

    public void setEnterSignAfterChangeStatus(String enterSignAfterChangeStatus) {
        this.enterSignAfterChangeStatus = enterSignAfterChangeStatus;
    }
}
