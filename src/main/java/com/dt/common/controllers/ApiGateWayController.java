package com.dt.common.controllers;

import com.dt.common.service.GateWayService;
import com.dt.common.vo.RequestVO;
import com.dt.common.vo.ResponseVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created by VerRan.Liu on 2017/9/20.
 */
@RestController
@RequestMapping("gateway")
//通过CrossOrigin支持跨域访问,如果不添加前端访问时提示类型格式不正确
@CrossOrigin
public class ApiGateWayController {

    @Autowired
    GateWayService gateWayService;

    @ApiOperation(value="api_gateway_v1", notes="API网关接口V1.0")
    @ApiImplicitParam(name = "request", value = "请求信息，具体参见微服务接口协议", required = true, dataType = "Json")
    @RequestMapping(value="/v1/api",method = RequestMethod.POST)
    public ResponseVO api_v1(@RequestBody RequestVO request){
        //1. 校验token
        ResponseVO  responseVO = gateWayService.validToken(request.getToken());
        if(!StringUtils.isEmpty(responseVO.getCode())){
            return responseVO;
        }
        //2. 根据服务编码获取对应的url
        StringBuffer url =new StringBuffer();
        responseVO = gateWayService.fetchServiceUrl(request.getServiceCode(),url);
        if(!StringUtils.isEmpty(responseVO.getCode())){
            return responseVO;
        }
        //3. 使用restTemplate 调用实际服务并返回数据
                //负载均衡和断路器能力后续添加
        responseVO = gateWayService.invokeRestService(url.toString(),request.getInput());
        return responseVO;
    }



}
