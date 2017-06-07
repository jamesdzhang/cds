package com.tuniu.car.basic.vo.forward.coc.cancel;

import com.tuniu.car.basic.param.build.intf.ICocOrderParam;

/**
 * Created by zhangyaping on 2017/5/27.
 */
public class CocCancelDto implements ICocOrderParam {

    private Integer orderId;
    private Integer cancel = 1;//参考wiki【http://wiki.tuniu.org/pages/viewpage.action?pageId=70778756】1代表取消

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCancel() {
        return cancel;
    }

    public void setCancel(Integer cancel) {
        this.cancel = cancel;
    }
}
