package com.tuniu.car.basic.vo.request;

import com.tuniu.car.basic.vo.bean.validation.constant.BeanValidationConstants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by zhangyaping on 2017/5/18.
 */
public class OrderQueryRequestDetail implements RequestDetail{

    @NotNull(message= BeanValidationConstants.NOTNULL)
    @Size(min=1,max=20,message = BeanValidationConstants.RANGE+"(1-20)")
    private List<OrderQueryVo> tnOrderList;

    public List<OrderQueryVo> getTnOrderList() {
        return tnOrderList;
    }

    public void setTnOrderList(List<OrderQueryVo> tnOrderList) {
        this.tnOrderList = tnOrderList;
    }
}
