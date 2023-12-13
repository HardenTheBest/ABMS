package com.zjut.abms.controller;

import com.zjut.abms.service.AdminService;
import com.zjut.abms.service.AgencyService;
import com.zjut.abms.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
//@RequestMapping("")
public class CommonController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AgencyService agencyService;

    @PostMapping("login")
    public Result login(@RequestBody Map<String, String> requestData) {
        String type = requestData.get("type");
        Result result = null;
        if (type.equals("admin")) {
            result = adminService.login(requestData.get("username"), requestData.get("password"));
        }else if(type.equals("agency")){
            result = agencyService.login(requestData.get("username"), requestData.get("password"));
        }

        return result;
    }
}
