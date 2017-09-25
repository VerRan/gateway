package com.dt.common.service;

import com.dt.common.models.Cust;
import com.dt.common.repository.CustRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by VerRan.Liu on 2017/9/18.
 */
@Service
public class CustService {
    @Autowired
    CustRepository custRepository ;
    public Cust findByName(String name){
        return custRepository.findByName(name);
    }

    public  Long save(Cust cust) {
       return  custRepository.save(cust).getId();
    }
}
