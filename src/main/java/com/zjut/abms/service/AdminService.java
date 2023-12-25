package com.zjut.abms.service;

import com.zjut.abms.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjut.abms.utils.Result;

/**
* @author l
* @description 针对表【admin】的数据库操作Service
* @createDate 2023-12-09 13:11:44
*/
public interface AdminService extends IService<Admin> {

    Result login(String username, String password);

    Result register(String username, String password);
}
