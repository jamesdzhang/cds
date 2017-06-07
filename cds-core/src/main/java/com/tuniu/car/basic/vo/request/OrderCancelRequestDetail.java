package com.tuniu.car.basic.vo.request;

import com.tuniu.car.basic.vo.bean.validation.constant.BeanValidationConstants;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by zhangyaping on 2017/5/18.
 */
public class OrderCancelRequestDetail implements RequestDetail{

    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String orderId;
    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String lossSchemeId;
    @NotNull(message= BeanValidationConstants.NOTNULL)
    @Valid
    private CustomerInfoVo customerInfo;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getLossSchemeId() {
        return lossSchemeId;
    }

    public void setLossSchemeId(String lossSchemeId) {
        this.lossSchemeId = lossSchemeId;
    }

    public CustomerInfoVo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfoVo customerInfo) {
        this.customerInfo = customerInfo;
    }
}
