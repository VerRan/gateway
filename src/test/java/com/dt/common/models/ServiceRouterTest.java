package com.dt.common.models;

import com.dt.common.repository.ServiceRouterRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by VerRan.Liu on 2017/9/20.
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class ServiceRouterTest {
    @Autowired
    ServiceRouterRepository serviceRouterRepository;

    @Before
    public void init(){
        ServiceRouter serviceRouter=new ServiceRouter();
        serviceRouter.setServiceCode("PROD_PROD_LIST");
        serviceRouter.setUrl("http://localhost:8080/prod/query");
        serviceRouter.setSts("A");
        serviceRouter.setCreateDate(new Date());
        serviceRouter.setAuthor("zhangyan");
        serviceRouter.setRemarks("产品列表查询");
        serviceRouterRepository.save(serviceRouter);
    }

    @Test
    public void testFindSRByCode(){
        Assert.assertEquals(1,serviceRouterRepository.findAll().size());
        Assert.assertEquals("http://localhost:8080/prod/query",serviceRouterRepository.findSRByServiceCode("PROD_PROD_LIST").getUrl());
    }
}
