package com.dt.common.repository;

import com.dt.common.models.Cust;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

/**
 * Created by VerRan.Liu on 2017/9/18.
 */
@RepositoryRestController
public interface CustRepository extends JpaRepository<Cust,Long> {
    public Cust findByName(String name);
}
