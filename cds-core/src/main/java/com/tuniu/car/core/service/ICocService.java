package com.tuniu.car.core.service;

import com.tuniu.car.basic.vo.request.OrderRequestDto;
import com.tuniu.car.basic.vo.response.ResponseVo;

/**
 * Created by zhangyaping on 2017/5/22.
 */
public interface ICocService {

    ResponseVo createOrder(OrderRequestDto orderRequestDto);

    ResponseVo lossCheck(OrderRequestDto orderRequestDto);

    ResponseVo cancelOrder(OrderRequestDto orderRequestDto);

    ResponseVo queryStatus(OrderRequestDto orderRequestDto);

    ResponseVo feedback(OrderRequestDto orderRequestDto);

}
