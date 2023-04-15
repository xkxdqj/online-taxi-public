package com.msb.servicepassengeruser.service;

import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * @Author:maojianfeng
 * @Date:2023-04-09-22:29
 * @Description:
 * @version:1.0
 */
@Service
public class PassengerUserService {

    /**
     * 根据手机号查询用户是否已注册
     * @param phone
     * @return
     */
    public ResponseResult loginOrRegister(String phone){

        return ResponseResult.success();
    }
}
