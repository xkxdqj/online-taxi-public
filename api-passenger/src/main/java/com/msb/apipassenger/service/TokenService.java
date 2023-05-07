package com.msb.apipassenger.service;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.msb.internalcommon.constant.CommonStatusEnum;
import com.msb.internalcommon.constant.TokenConstants;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.dto.TokenResult;
import com.msb.internalcommon.response.TokenResponse;
import com.msb.internalcommon.util.JwtUtil;
import com.msb.internalcommon.util.RedisPrefixUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author:maojianfeng
 * @Date:2023-05-07-14:38
 * @Description:
 * @version:1.0
 */
@Service
public class TokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //刷新token
    public ResponseResult refreshToken(String refreshTokenSrc) {
        //校验token
        TokenResult tokenResult = JwtUtil.checkToken(refreshTokenSrc);
        if(tokenResult==null){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getMessage());
        }
        String phone = tokenResult.getPhone();
        String identity = tokenResult.getIdentity();

        // 去读取redis中 的refreshToken
        String refreshTokenKey = RedisPrefixUtils.generatorTokenKey(phone,identity, TokenConstants.REFRESH_TOKEN_TYPE);
        String refreshTokenRedis = stringRedisTemplate.opsForValue().get(refreshTokenKey);

        // 校验refreshToken
        if ((StringUtils.isBlank(refreshTokenRedis))  || (!refreshTokenSrc.trim().equals(refreshTokenRedis.trim()))){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getMessage());
        }

        // 生成双token
        String refreshToken = JwtUtil.generatorToken(phone,identity,TokenConstants.REFRESH_TOKEN_TYPE);
        String accessToken = JwtUtil.generatorToken(phone,identity,TokenConstants.ACCESS_TOKEN_TYPE);

        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(phone,identity,TokenConstants.ACCESS_TOKEN_TYPE);

        stringRedisTemplate.opsForValue().set(accessTokenKey , accessToken , 30, TimeUnit.SECONDS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey , refreshToken , 50, TimeUnit.SECONDS);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setAccessToken(accessToken);

        return ResponseResult.success(tokenResponse);
    }
}
