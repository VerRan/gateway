package com.dt.common.config;

import com.dt.common.annoation.DevelopmentE;
import com.dt.common.annoation.TestE;
import com.dt.common.models.ServiceRouter;
import com.dt.common.repository.ServiceRouterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * Created by VerRan.Liu on 2017/9/20.
 */
@Configuration
@DevelopmentE  //开发环境下初始化部分数据用于验证
public class InitDataForTest {

    @Bean
    public CommandLineRunner loadData(ServiceRouterRepository serviceRouterRepository){
        return (args)->{
            ServiceRouter serviceRouter=new ServiceRouter();
            serviceRouter.setServiceCode("PROD_PROD_LIST");
//            serviceRouter.setUrl("http://114.115.139.228:6060/product/list");
            serviceRouter.setUrl("http://10.100.14.65:6100/product/list");
            serviceRouter.setSts("A");
            serviceRouter.setCreateDate(new Date());
            serviceRouter.setAuthor("zhangyan");
            serviceRouter.setRemarks("产品信息查询");
            serviceRouterRepository.save(serviceRouter);

            serviceRouter=new ServiceRouter();
            serviceRouter.setServiceCode("PROD_PROD_POST");
            serviceRouter.setUrl("http://10.100.14.65:6100/product/list");
            serviceRouter.setSts("A");
            serviceRouter.setCreateDate(new Date());
            serviceRouter.setAuthor("zhangyan");
            serviceRouter.setRemarks("产品信息发布");


            serviceRouterRepository.save(serviceRouter);
        };
    }
}
