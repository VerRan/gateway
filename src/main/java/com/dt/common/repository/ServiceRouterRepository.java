package com.dt.common.repository;

import com.dt.common.models.ServiceRouter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by VerRan.Liu on 2017/9/20.
 */
@Repository
public interface ServiceRouterRepository extends JpaRepository<ServiceRouter,Long> {
    public ServiceRouter findSRByServiceCode(String serviceCode);
}
