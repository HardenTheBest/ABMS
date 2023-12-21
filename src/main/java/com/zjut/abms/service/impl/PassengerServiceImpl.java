package com.zjut.abms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjut.abms.pojo.Passenger;
import com.zjut.abms.service.PassengerService;
import com.zjut.abms.mapper.PassengerMapper;
import com.zjut.abms.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author l
* @description 针对表【passenger】的数据库操作Service实现
* @createDate 2023-12-21 20:30:45
*/
@Service
public class PassengerServiceImpl extends ServiceImpl<PassengerMapper, Passenger>
    implements PassengerService{

    @Autowired
    private PassengerMapper passengerMapper;
    @Override
    public Result selectAllPassenger() {
        List<Passenger> passengers = passengerMapper.selectList(null);
        return Result.ok(passengers);
    }
}




