package com.zjut.abms.controller;

import com.zjut.abms.pojo.Admin;
import com.zjut.abms.service.AdminService;
import com.zjut.abms.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("register")
    public Result register(@RequestBody Admin admin){
        Result result = adminService.register(admin);
        return result;
    }
}
