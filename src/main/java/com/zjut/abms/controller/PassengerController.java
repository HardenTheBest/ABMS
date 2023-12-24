package com.zjut.abms.controller;

import com.zjut.abms.pojo.Passenger;
import com.zjut.abms.service.PassengerService;
import com.zjut.abms.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("passenger")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @PostMapping("register")
    public Result register(@RequestBody Passenger passenger){
        Result result = passengerService.register(passenger);
        return result;
    }
}
