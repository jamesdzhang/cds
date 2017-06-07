package com.tuniu.car.basic.vo.request;

import com.tuniu.car.basic.vo.bean.validation.constant.BeanValidationConstants;

import javax.validation.constraints.NotNull;

/**
 * Created by zhangyaping on 2017/5/18.
 */
public class LossCheckRequestDetail implements RequestDetail{

    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
