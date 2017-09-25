package com.dt.common.controllers;

import com.dt.common.models.Cust;
import com.dt.common.service.CustService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by VerRan.Liu on 2017/9/18.
 */
@RestController
@RequestMapping("/cust")
public class CustController {
    @Autowired
    CustService custService;

    @ApiOperation(value="查询", notes="根据客户名称查询客户信息")
    @ApiImplicitParam(name = "name", value = "客户名称", required = true, dataType = "Json")
    @RequestMapping(value="/query",method = RequestMethod.GET)
    public Cust query(@RequestParam(value = "name" ,required = true,defaultValue = "cms")
                      String name ){
        return custService.findByName(name);
    }

    @ApiOperation(value="保存", notes="保存客户信息")
    @ApiImplicitParam(name = "cust", value = "客户信息", required = true, dataType = "Json")
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public Long save(@RequestBody Cust cust){
       return custService.save(cust);
    }
}
