package com.zjut.abms.service;

import com.zjut.abms.pojo.Passenger;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjut.abms.utils.Result;

/**
* @author l
* @description 针对表【passenger】的数据库操作Service
* @createDate 2023-12-21 20:30:45
*/
public interface PassengerService extends IService<Passenger> {

    Result selectAllPassenger();
}
