package com.tuniu.car.basic.vo.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.tuniu.car.basic.annotation.BeanValid;
import com.tuniu.car.basic.mongo.db.SeqPacket;
import com.tuniu.car.basic.vo.bean.validation.constant.BeanValidationConstants;
import com.tuniu.car.basic.vo.detail.factory.RequestDetailFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by zhangyaping on 2017/5/18.
 */
public class OrderRequestDto implements SeqPacket{

    @NotNull(message= BeanValidationConstants.NOTNULL)
    @Size(max = 20, message = BeanValidationConstants.GREATTHAN+20)
    private String requestId;

    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String requestType;

    private String retailId;//从RDP转发过来的参数

    @NotNull(message= BeanValidationConstants.NOTNULL)
    @Size(max = 32, message = BeanValidationConstants.GREATTHAN+32)
    private String requestSeq;


    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String requestSource;


    @NotNull(message= BeanValidationConstants.NOTNULL)
    @Valid
    private RequestDetail requestDetail;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestSeq() {
        return requestSeq;
    }

    public void setRequestSeq(String requestSeq) {
        this.requestSeq = requestSeq;
    }

    public String getRequestSource() {
        return requestSource;
    }

    public void setRequestSource(String requestSource) {
        this.requestSource = requestSource;
    }

    public RequestDetail getRequestDetail() {
        return requestDetail;
    }

    public void setRequestDetail(RequestDetail requestDetail) {
        this.requestDetail = requestDetail;
    }

    @JsonCreator
    public OrderRequestDto(@JsonProperty(value = "requestType") String requestType,
                           @JsonProperty(value = "requestDetail") JsonNode requestDetail) {
        this.requestType = requestType;
        this.requestDetail =  RequestDetailFactory.getRequestDetailType(requestType, requestDetail);
    }

    public OrderRequestDto(){

    }

    public String getRetailId() {
        return retailId;
    }

    public void setRetailId(String retailId) {
        this.retailId = retailId;
    }
}
