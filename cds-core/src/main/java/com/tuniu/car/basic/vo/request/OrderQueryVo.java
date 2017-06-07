package com.tuniu.car.basic.vo.request;

import com.tuniu.car.basic.param.build.intf.ICocOrderParam;
import com.tuniu.car.basic.vo.bean.validation.constant.BeanValidationConstants;

import javax.validation.constraints.NotNull;

/**
 * Created by zhangyaping on 2017/5/19.
 */
public class OrderQueryVo implements ICocOrderParam{

    @NotNull(message= BeanValidationConstants.NOTNULL)
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
