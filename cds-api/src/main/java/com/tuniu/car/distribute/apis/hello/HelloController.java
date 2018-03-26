package com.tuniu.car.distribute.apis.hello;

import com.tuniu.car.basic.annotation.BeanValid;
import com.tuniu.car.basic.vo.request.HelloVo;
import com.tuniu.operation.platform.tsg.base.core.annotation.Json;
import com.tuniu.operation.platform.tsg.base.core.annotation.ResponseJson;
import com.tuniu.operation.platform.tsg.base.core.utils.JsonUtil;
import com.tuniu.operation.platform.tsg.base.core.utils.ResponseVo;
import com.tuniu.operation.platform.tsg.base.rest.RestClient;
import com.tuniu.operation.platform.tsg.client.annotation.TSPServiceInfo;
import com.tuniu.operation.platform.tsg.client.caller.rest.RequestParam;
import com.tuniu.operation.platform.tsg.client.caller.rest.ResponseResult;
import com.tuniu.operation.platform.tsg.client.proxy.tsg.TSPCommonClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by zhangyaping on 2017/5/15.
 */
@Controller
@RequestMapping("/cds")
public class HelloController {

    @Resource
    private TSPCommonClient tspCommonClient;

    private String ServiceA = "PRD.NM.ProductController.queryProducts";
    private String ServiceOrderUrl = "CAR.CDS.CarOrderApiController.createOrder";

    @RequestMapping(value = "/hi",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseJson
    @BeanValid
    public ResponseVo sayHi_(@Json HelloVo helloVo) throws Exception{
        ResponseVo vo = new ResponseVo();
        ResponseVo vo1 = new ResponseVo();

        RequestParam requestParam1 = new RequestParam();
        requestParam1.setServiceName(ServiceA);
        requestParam1.setData(JsonUtil.toJsonString(helloVo));

        RequestParam requestParam = new RequestParam();
        requestParam.setServiceName(ServiceOrderUrl);
        requestParam.setData(JsonUtil.toJsonString(helloVo));

//        ResponseResult result1 = tspCommonClient.responseCaller(requestParam1);
        ResponseResult result = tspCommonClient.responseCaller(requestParam);
        vo.setData(result);
        return vo;
    }

}
