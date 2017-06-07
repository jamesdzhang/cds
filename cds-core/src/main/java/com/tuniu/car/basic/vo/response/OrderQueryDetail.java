package com.tuniu.car.basic.vo.response;

import java.util.List;

/**
 * Created by zhangyaping on 2017/5/19.
 */
public class OrderQueryDetail {

    public List<OrderStatusVo> getQueryResult() {
        return queryResult;
    }

    public void setQueryResult(List<OrderStatusVo> queryResult) {
        this.queryResult = queryResult;
    }

    private List<OrderStatusVo> queryResult;
}
