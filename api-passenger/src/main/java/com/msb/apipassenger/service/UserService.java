package com.msb.apipassenger.service;

import com.msb.apipassenger.remote.ServicePassengerUserFeign;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.dto.TokenResult;
import com.msb.internalcommon.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:maojianfeng
 * @Date:2023-05-08-0:12
 * @Description:根据token获取用户信息
 * @version:1.0
 */
@Service
public class UserService {

    @Autowired
    private ServicePassengerUserFeign servicePassengerUserFeign;

    public ResponseResult getUser(String accessToken) {
        //解析token,拿到token里的手机号
        TokenResult tokenResult = JwtUtil.checkToken(accessToken);
        String phone = tokenResult.getPhone();
        //根据手机号查询用户信息
        ResponseResult responseResult = servicePassengerUserFeign.getUserByPhone(phone);
        return responseResult;
    }
}
