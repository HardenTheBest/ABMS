package com.zjut.abms.controller;

import com.zjut.abms.pojo.Agency;
import com.zjut.abms.service.AgencyService;
import com.zjut.abms.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("agency")
public class AgencyController {

    @Autowired
    private AgencyService AgencyService;

    @PostMapping("register")
    public Result register(@RequestBody Agency agency){
        Result result = AgencyService.register(agency);
        return result;
    }
}
