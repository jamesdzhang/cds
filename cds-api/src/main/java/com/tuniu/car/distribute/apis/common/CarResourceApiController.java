package com.tuniu.car.distribute.apis.common;

import com.tuniu.car.basic.vo.request.HelloVo;
import com.tuniu.operation.platform.tsg.base.core.annotation.Json;
import com.tuniu.operation.platform.tsg.base.core.annotation.ResponseJson;
import com.tuniu.car.basic.vo.response.ResponseVo;
import com.tuniu.operation.platform.tsg.client.annotation.TSPServiceInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhangyaping on 2017/5/15.
 */
@Controller
@RequestMapping("/CDS")
public class CarResourceApiController {


    @RequestMapping(value = "/search", method = {RequestMethod.POST})
    @TSPServiceInfo(name = "CAR.CDS.CarResourceApiController.search", description = "Resource search")
    @ResponseJson
    public ResponseVo search(@Json HelloVo helloVo) {
        ResponseVo vo = new ResponseVo();
        vo.setSuccess(helloVo.getAge() > 20 ? true : false);
        vo.setMsg(helloVo.getName());
        vo.setData(helloVo);
        return vo;
    }


    @RequestMapping(value = "/productInfo", method = {RequestMethod.POST})
    @TSPServiceInfo(name = "CAR.CDS.CarResourceApiController.productInfo", description = "Query product info")
    @ResponseJson
    public ResponseVo productInfo(@Json HelloVo helloVo) {
        ResponseVo vo = new ResponseVo();
        vo.setSuccess(helloVo.getAge() > 20 ? true : false);
        vo.setMsg(helloVo.getName());
        vo.setData(helloVo);
        return vo;
    }
}
