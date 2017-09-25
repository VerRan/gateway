package com.dt.common.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by VerRan.Liu on 2017/9/18.
 * RestTemplate.exchange()方法参数说明
 * 说明：
 1）url: 请求地址；
 2）method: 请求类型(如：POST,PUT,DELETE,GET)；
 3）requestEntity: 请求实体，封装请求头，请求内容
 4）responseType: 响应类型，根据服务接口的返回类型决定
 5）uriVariables: url中参数变量值

 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustControllerTest {
    @LocalServerPort
    private  int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private  String BASE_URL="";

    private String SAVE_URL=BASE_URL+"/cust/save";
    private String QUERY_URL=BASE_URL+"/cust/query?name=刘恒涛";
    private String DELETE_URL=BASE_URL+"/cust/delete";


    @Before
    public  void init(){
        BASE_URL="http://localhost:"+port;
    }

    @Test
    public void   testIndex(){
        assertThat(this.testRestTemplate.getForObject(BASE_URL+"/"
                ,String.class)).contains("API");
    }

    @Before
    public void testSave(){
        String reqJsonStr = "{\"name\":\"刘恒涛\", \"age\":\"32\",\"birthDay\":\"1985-10-15\",\"telephone\":\"15319746639\"}";
        HttpHeaders headers = new HttpHeaders(); headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(reqJsonStr,headers);
        ResponseEntity<Long> resp = testRestTemplate.exchange(SAVE_URL, HttpMethod.POST, entity, Long.class);
    }

    @Test
    public void testCustQuery(){
        assertThat(this.testRestTemplate.getForObject(QUERY_URL, String.class)).contains("32");
    }

//    @Test
//    public void testCustUpdate(){
//        String reqJsonStr = "{\"id\":227,\"code\":\"updateCC\", \"group\":\"UPDATE\",\"content\":\"updateCT\", \"order\":9}";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> entity = new HttpEntity<String>(reqJsonStr,headers);
//        ResponseEntity<Map> resp = testRestTemplate.exchange(DIC_DATA_URL, HttpMethod.PUT, entity, Map.class);
//    }
//
//    @Test
//    public void testCustDelete(){
//        ResponseEntity<Long> resp = testRestTemplate.exchange(DELETE_URL + "?id={id}",
//                HttpMethod.DELETE, null, Long.class);
//    }

}
