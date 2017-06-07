package com.tuniu.car.distribute.apis.common;

import com.tuniu.car.basic.annotation.BeanValid;
import com.tuniu.car.basic.annotation.FromRDP;
import com.tuniu.car.basic.vo.request.HelloVo;
import com.tuniu.car.basic.vo.request.OrderRequestDto;
import com.tuniu.car.core.service.ICocService;
import com.tuniu.operation.platform.tsg.base.core.annotation.Json;
import com.tuniu.operation.platform.tsg.base.core.annotation.ResponseJson;
import com.tuniu.car.basic.vo.response.ResponseVo;
import com.tuniu.operation.platform.tsg.client.annotation.TSPServiceInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by zhangyaping on 2017/5/15.
 */
@Controller
@RequestMapping("/car")
public class CarOrderApiController {

    @Resource(name="cocServiceImpl")
    private ICocService cocService;

    @RequestMapping(value = "/createOrder",method = {RequestMethod.POST,RequestMethod.GET})
    @TSPServiceInfo(name = "CAR.CDS.CarOrderApiController.createOrder", description = "Create order")
    @BeanValid
    @ResponseJson
    public ResponseVo sayHi(@Json HelloVo helloVo){
        ResponseVo vo = new ResponseVo();
        vo.setSuccess(helloVo.getAge()>20?true:false);
        vo.setMsg(helloVo.getName());
        vo.setData(helloVo);
        return vo;
    }


    @RequestMapping(value = "/book",method = {RequestMethod.POST})
    @TSPServiceInfo(name = "CAR.CDS.CarOrderApiController.book", description = "create order")
    @ResponseJson
    @BeanValid
    public ResponseVo createOrder(@Json @FromRDP OrderRequestDto orderRequestDto){
        ResponseVo vo = cocService.createOrder(orderRequestDto);
        return vo;
    }


    @RequestMapping(value = "/cancel",method = {RequestMethod.POST})
    @TSPServiceInfo(name = "CAR.CDS.CarOrderApiController.cancel", description = "Cancel order")
    @ResponseJson
    @BeanValid
    public ResponseVo cancelOrder(@Json @FromRDP OrderRequestDto orderRequestDto){
        ResponseVo vo = cocService.cancelOrder(orderRequestDto);
        return vo;
    }


    @RequestMapping(value = "/lossCheck",method = {RequestMethod.POST})
    @TSPServiceInfo(name = "CAR.CDS.CarOrderApiController.lossCheck", description = "Order lossCheck")
    @ResponseJson
    @BeanValid
    public ResponseVo lossCheck(@Json @FromRDP OrderRequestDto orderRequestDto){
        ResponseVo vo = cocService.lossCheck(orderRequestDto);
        return vo;
    }


    @RequestMapping(value = "/queryStatus",method = {RequestMethod.POST})
    @TSPServiceInfo(name = "CAR.CDS.CarOrderApiController.queryStatus", description = "Query order status")
    @ResponseJson
    @BeanValid
    public ResponseVo queryStatus(@Json @FromRDP OrderRequestDto orderRequestDto){
        return cocService.queryStatus(orderRequestDto);
    }
}
