package com.dt.common.service;

import com.dt.common.models.ServiceRouter;
import com.dt.common.repository.ServiceRouterRepository;
import com.dt.common.vo.ResponseVO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * Created by VerRan.Liu on 2017/9/20.
 */
@Service
public class GateWayService {

    private ResponseVO responseVO=new ResponseVO();

    @Autowired
    ServiceRouterRepository serviceRouterRepository;

    @Autowired
    RestTemplate restTemplate;

    //注入RestTemplate
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    //用于处理json
    Gson gson =new Gson();

    /***
     * @param token
     * @return ResponseVo
     * 用于校验token的合法性
     * **/
    public ResponseVO validToken(String token) {
        //2. 校验token
        if(StringUtils.isEmpty(token)){
            responseVO.setCode("3001");
            responseVO.setMessage("Token不能为空");
            return responseVO;
        }else{
            //校验token ，如果采用redis这查询 这里是可以使用spring-session-jdbc 或者redis实现
        }
        return responseVO;
    }

    /***
     * @param
     *
     * */
    public ResponseVO fetchServiceUrl(String serviceCode, StringBuffer url) {
        if(StringUtils.isEmpty(serviceCode)){
            responseVO.setCode("3001");
            responseVO.setMessage("服务编码不能为空");
            return responseVO;
        }

            ServiceRouter sr=
                    serviceRouterRepository.findSRByServiceCode(serviceCode);
            if(sr==null||StringUtils.isEmpty(sr.getUrl())){
                responseVO.setCode("3001");
                responseVO.setMessage("服务编码"+serviceCode+"未配置服务路由信息");
                return responseVO;
            }
            url.append(sr.getUrl());
        return responseVO;
    }

    public ResponseVO invokeRestService(String url, Object data) {
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity=new HttpEntity(gson.toJson(data),httpHeaders);
        ResponseEntity<String> resp =restTemplate.exchange(url, HttpMethod.POST,httpEntity,String.class);
        responseVO= gson.fromJson(resp.getBody(),ResponseVO.class);
        return responseVO;
    }
}
