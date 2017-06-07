package com.tuniu.car.basic.vo.forward.coc.loss.check;

import com.tuniu.car.basic.param.build.intf.ICocOrderParam;

/**
 * Created by zhangyaping on 2017/5/27.
 */
public class CocLossCheckDto implements ICocOrderParam {

    private Integer orderId;
    private String cancelReason;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }
}
