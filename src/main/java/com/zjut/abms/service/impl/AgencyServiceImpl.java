package com.zjut.abms.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjut.abms.pojo.Agency;
import com.zjut.abms.service.AgencyService;
import com.zjut.abms.mapper.AgencyMapper;
import com.zjut.abms.utils.JwtHelper;
import com.zjut.abms.utils.MD5Util;
import com.zjut.abms.utils.Result;
import com.zjut.abms.utils.ResultCodeEnum;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author l
 * @description 针对表【agency】的数据库操作Service实现
 * @createDate 2023-12-09 15:00:39
 */
@Service
public class AgencyServiceImpl extends ServiceImpl<AgencyMapper, Agency>
        implements AgencyService {

    @Autowired
    private AgencyMapper agencyMapper;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result login(String username, String password) {
        //根据条件查询用户
        LambdaQueryWrapper<Agency> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Agency::getUsername, username);
        Agency loginAgency = agencyMapper.selectOne(lambdaQueryWrapper);

        if (loginAgency == null) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        //对比密码
        if (!StringUtils.isEmpty(password) && MD5Util.encrypt(password).equals(loginAgency.getPassword())) {
            //登录成功
            //根据id生成token
            String token = jwtHelper.createToken(Long.valueOf(loginAgency.getId()));
            //将token封装成result返回
            Map data = new HashMap();
            data.put("token", token);

            return Result.ok(data);
        }
        //密码错误
        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result register(String username, String password) {
        LambdaQueryWrapper<Agency> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Agency::getUsername, username);
        Long count = agencyMapper.selectCount(lambdaQueryWrapper);

        if (count > 0) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }

        String safePassword = MD5Util.encrypt(password);
        Agency agency = new Agency();
        agency.setUsername(username);
        agency.setPassword(safePassword);
        agencyMapper.insert(agency);
        return Result.ok(null);
    }
}




