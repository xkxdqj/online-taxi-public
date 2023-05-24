package com.msb.servicepassengeruser.controller;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.VerificationCodeDTO;
import com.msb.servicepassengeruser.service.PassengerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:maojianfeng
 * @Date:2023-04-09-22:15
 * @Description:
 * @version:1.0
 */
@RestController
public class PassengerUserController {

    @Autowired
    PassengerUserService passengerUserService;

    @RequestMapping(method= RequestMethod.POST,value = "/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO){

        //获取手机号
        String phone = verificationCodeDTO.getPhone();

        //根据手机号查询用户是否已注册
        ResponseResult responseResult = passengerUserService.loginOrRegister(phone);
        return responseResult;
    }

    @RequestMapping(method= RequestMethod.GET,value = "/user/{phone}")
    public ResponseResult getUserByPhone(@PathVariable String phone){
        ResponseResult responseResult = passengerUserService.getUserByPhone(phone);
        return responseResult;
    }
}
