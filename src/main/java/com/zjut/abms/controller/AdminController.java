package com.zjut.abms.controller;

import com.zjut.abms.pojo.Admin;
import com.zjut.abms.service.AdminService;
import com.zjut.abms.service.PassengerService;
import com.zjut.abms.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private PassengerService passengerService;

//    @PostMapping("register")
//    public Result register(@RequestBody Admin admin){
//        Result result = adminService.register(admin);
//        return result;
//    }

    @GetMapping("selectAllPassenger")
    public Result selectAllPassenger(){
        Result result = passengerService.selectAllPassenger();
        return result;
    }
}
