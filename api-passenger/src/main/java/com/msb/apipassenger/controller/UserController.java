package com.msb.apipassenger.controller;

import com.msb.apipassenger.service.UserService;
import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:maojianfeng
 * @Date:2023-05-08-0:10
 * @Description:
 * @version:1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseResult getUser(HttpServletRequest request){
        //获取请求头中的token
        String accessToken = request.getHeader("Authorization");

        ResponseResult responseResult = userService.getUser(accessToken);
        return responseResult;
    }
}
