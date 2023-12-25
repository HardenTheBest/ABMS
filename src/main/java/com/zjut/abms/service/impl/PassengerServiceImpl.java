package com.zjut.abms.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjut.abms.pojo.Passenger;
import com.zjut.abms.service.PassengerService;
import com.zjut.abms.mapper.PassengerMapper;
import com.zjut.abms.utils.JwtHelper;
import com.zjut.abms.utils.MD5Util;
import com.zjut.abms.utils.Result;
import com.zjut.abms.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author l
 * @description 针对表【passenger】的数据库操作Service实现
 * @createDate 2023-12-21 20:30:45
 */
@Service
public class PassengerServiceImpl extends ServiceImpl<PassengerMapper, Passenger>
        implements PassengerService {

    @Autowired
    private PassengerMapper passengerMapper;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result selectAllPassenger() {
        List<Passenger> passengers = passengerMapper.selectPassengers();
        return Result.ok(passengers);
    }

    @Override
    public Result login(String username, String password) {
        LambdaQueryWrapper<Passenger> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Passenger::getUsername, username);
        Passenger loginPassenger = passengerMapper.selectOne(lambdaQueryWrapper);

        if (loginPassenger == null) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        // 对比密码
        if (!StringUtils.isEmpty(password) && MD5Util.encrypt(password).equals(loginPassenger.getPassword())) {
            String token = jwtHelper.createToken(Long.valueOf(loginPassenger.getId()));
            Map data = new HashMap();
            data.put("token", token);
            return Result.ok(data);
        }
        //密码错误
        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result register(String username, String password) {
        System.out.println(username);
        LambdaQueryWrapper<Passenger> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Passenger::getUsername,username);
        Long count = passengerMapper.selectCount(lambdaQueryWrapper);

        if(count > 0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        String safePassword = MD5Util.encrypt(password);
        Passenger passenger = new Passenger();
        passenger.setUsername(username);
        passenger.setPassword(safePassword);
        System.out.println(passenger);
        passengerMapper.insert(passenger);
        return Result.ok(null);
    }
}




