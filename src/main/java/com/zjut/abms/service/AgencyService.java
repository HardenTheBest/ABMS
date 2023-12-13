package com.zjut.abms.service;

import com.zjut.abms.pojo.Agency;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjut.abms.utils.Result;

/**
* @author l
* @description 针对表【agency】的数据库操作Service
* @createDate 2023-12-09 15:00:39
*/
public interface AgencyService extends IService<Agency> {

    Result login(String username, String password);

    Result register(Agency agency);
}
