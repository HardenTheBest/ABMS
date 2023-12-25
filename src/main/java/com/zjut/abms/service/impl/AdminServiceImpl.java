package com.zjut.abms.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjut.abms.pojo.Admin;
import com.zjut.abms.service.AdminService;
import com.zjut.abms.mapper.AdminMapper;
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
* @description 针对表【admin】的数据库操作Service实现
* @createDate 2023-12-09 13:11:44
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private JwtHelper jwtHelper;
    @Override
    public Result login(String username, String password) {
        //根据条件查询数据库
        LambdaQueryWrapper<Admin>lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Admin::getUsername, username);
        Admin loginAdmin = adminMapper.selectOne(lambdaQueryWrapper);

        if(loginAdmin == null){
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        //对比密码
        if(!StringUtils.isEmpty(password)&& MD5Util.encrypt(password).equals(loginAdmin.getPassword())){
            //登录成功
            //根据id生成token
            String token =jwtHelper.createToken(Long.valueOf(loginAdmin.getId()));
            //将token封装成result返回
            Map data = new HashMap();
            data.put("token", token);

            return Result.ok(data);
        }
        //密码错误
        return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result register(String username, String password) {
        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Admin::getUsername,username);
        Long count = adminMapper.selectCount(lambdaQueryWrapper);

        if(count > 0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }

        String safePassword = MD5Util.encrypt(password);
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(safePassword);
        adminMapper.insert(admin);
        return Result.ok(null);
    }
}




