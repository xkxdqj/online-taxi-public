package com.msb.apipassenger.controller;

import com.msb.apipassenger.service.TokenService;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:maojianfeng
 * @Date:2023-05-07-14:33
 * @Description:
 * @version:1.0
 */
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("refreshToken")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse){
        String refreshTokenSrc = tokenResponse.getRefreshToken();
        ResponseResult responseResult = tokenService.refreshToken(refreshTokenSrc);
        return responseResult;
    }
}
