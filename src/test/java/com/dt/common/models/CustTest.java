package com.dt.common.models;

import com.dt.common.models.Cust;
import com.dt.common.repository.CustRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Date;

/**
 * Created by VerRan.Liu on 2017/9/18.
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class CustTest {

    @Autowired
    CustRepository custRepository;

    @Before
    public void init(){
        Cust cust=new Cust();
        cust.setName("刘恒涛");
        cust.setAge(32);
        cust.setBirthDay(new Date());
        custRepository.save(cust);

        cust=new Cust();
        cust.setName("刘夏墨");
        cust.setAge(4);
        cust.setBirthDay(new Date());
        custRepository.save(cust);
    }

    @Test
    public void testFindAll(){
        Assert.assertEquals(2,custRepository.findAll().size());
    }

    @Test
    public void testFindByName(){
        Assert.assertEquals(4,custRepository.findByName("刘夏墨").getAge());
    }

}
