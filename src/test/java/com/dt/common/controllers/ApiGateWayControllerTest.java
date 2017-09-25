package com.dt.common.controllers;

import com.dt.common.models.ServiceRouter;
import com.dt.common.repository.ServiceRouterRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by VerRan.Liu on 2017/9/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiGateWayControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate ;

    private String BASE_URL ;

    @Autowired
    ServiceRouterRepository serviceRouterRepository;
    @Before
    public  void init(){
        BASE_URL="http://localhost:"+port;
        ServiceRouter serviceRouter=new ServiceRouter();
        serviceRouter.setServiceCode("CUST_SAVE");
        serviceRouter.setUrl("http://114.115.139.228:6060/cust/save");
//        serviceRouter.setUrl("http://localhost:6060/cust/save");
        serviceRouter.setSts("A");
        serviceRouter.setCreateDate(new Date());
        serviceRouter.setAuthor("liuhengtao");
        serviceRouter.setRemarks("客户信息登记");
        serviceRouterRepository.save(serviceRouter);
    }

    @Test
    public void testSRTokenIsNullError(){
        String url = BASE_URL+"/gateway/v1/api";
        String requestJson = "{\"token\":\"\",\"version\":\"v1\",\"serviceCode\":\"CUST_SAVE\"," +
                " \"input\":{\"name\":\"taotao\",\"birthDay\":\"2017-09-20T02:56:21.390Z\",\"age\":\"2\" } }";
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity=new HttpEntity(requestJson,httpHeaders);
        ResponseEntity<String> resp =testRestTemplate.exchange(url, HttpMethod.POST,httpEntity,String.class);
        Assert.assertEquals(200,resp.getStatusCode().value());
        Assert.assertEquals(true,resp.getBody().contains("Token不能为空"));
    }

    @Test
    public void testSRNoRouterConfigError(){
        String url = BASE_URL+"/gateway/v1/api";
        String requestJson = "{\"token\":\"123123\",\"version\":\"v1\",\"serviceCode\":\"CUST_SAVE2\"," +
                " \"input\":{\"name\":\"taotao\",\"birthDay\":\"2017-09-20T02:56:21.390Z\",\"age\":\"2\",\"teltephone\":\"15319746639\" } }";
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity=new HttpEntity(requestJson,httpHeaders);
        ResponseEntity<String> resp =testRestTemplate.exchange(url, HttpMethod.POST,httpEntity,String.class);
        Assert.assertEquals(true,resp.getBody().contains("未配置服务路由信息"));
    }


    @Test
    public void testServiceRouterOK(){
        String url = BASE_URL+"/gateway/v1/api";
        String requestJson = "{\"token\":\"123456\",\"version\":\"v1\",\"serviceCode\":\"CUST_SAVE\"," +
                " \"input\":{\"name\":\"taotao\",\"birthDay\":\"2017-09-20T02:56:21.390Z\",\"age\":\"2\" ,\"telephone\":\"15319746639\"} }";
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity=new HttpEntity(requestJson,httpHeaders);
        ResponseEntity<String> resp =testRestTemplate.exchange(url, HttpMethod.POST,httpEntity,String.class);
//        Assert.assertEquals(200,resp.getStatusCode().value());
//        System.out.println(resp.getBody());
//        Assert.assertEquals(true,resp.getBody().contains("成功"));
    }





}
