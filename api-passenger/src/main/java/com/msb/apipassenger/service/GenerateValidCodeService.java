package com.msb.apipassenger.service;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.msb.apipassenger.remote.ServicePassengerUserFeign;
import com.msb.apipassenger.remote.ServiceVerificationCodeFeign;
import com.msb.internalcommon.constant.IdentityConstants;
import com.msb.internalcommon.constant.TokenConstants;
import com.msb.internalcommon.constant.VerificationCheckEnum;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.VerificationCodeDTO;
import com.msb.internalcommon.response.NumberCodeResponse;
import com.msb.internalcommon.response.TokenResponse;
import com.msb.internalcommon.util.JwtUtil;
import com.msb.internalcommon.util.RedisPrefixUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


import java.util.concurrent.TimeUnit;

/**
 * @Author:maojianfeng
 * @Date:2023-02-07-21:23
 * @Description:生成验证码
 * @version:1.0
 */
@Service
public class GenerateValidCodeService {

    @Autowired
    private ServiceVerificationCodeFeign serviceVerificationCodeFeign;

    @Autowired
    private ServicePassengerUserFeign servicePassengerUserFeign;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult generateValidCode(VerificationCodeDTO verificationCodeDTO){
        //获取手机号
        String phone = verificationCodeDTO.getPhone();


        //调用阿里短信平台API生成验证码
        ResponseResult<NumberCodeResponse> numberCodeResopnse = serviceVerificationCodeFeign.getNumberCode(6);
        int numberCode = numberCodeResopnse.getData().getNumberCode();
        //验证码存入redis,设置过期时间
        String key = generateKeyByPhone(phone);
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);

        //返回响应结果
        return ResponseResult.success("");
    }

    /**
     * 根据手机号生成校验码
     * @param phone
     * @return
     */
    private String generateKeyByPhone(String phone) {
        return RedisPrefixUtils.generatorVerificationKey(phone);
    }

    /**
     * 验证码校验
     * @param verificationCodeDTO
     * @return
     */
    public ResponseResult checkVerificationCode(VerificationCodeDTO verificationCodeDTO){
        //根据phone和verificationCode校验验证码
        //1.获取手机号和verificationCode
        String phone = verificationCodeDTO.getPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();
        //2.取出redis中的验证码
        String codeRedis = stringRedisTemplate.opsForValue().get(generateKeyByPhone(phone));
        //校验失败,返回校验失败
        if(StringUtils.isBlank(codeRedis)){
            return ResponseResult.fail(VerificationCheckEnum.FAIL.getCode(),VerificationCheckEnum.FAIL.getMessage(),"");
        }else if(!verificationCode.equals(codeRedis)){
            return ResponseResult.fail(VerificationCheckEnum.FAIL.getCode(), VerificationCheckEnum.FAIL.getMessage(),"");
        }
        //校验成功,根据手机号查询用户,没有则插入
        ResponseResult responseResult = servicePassengerUserFeign.loginOrRegister(verificationCodeDTO);
        //生成token返回
        TokenResponse tokenResponse = new TokenResponse();

        String accessToken = JwtUtil.generatorToken(phone, IdentityConstants.PASSENGER_IDENTITY,TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtil.generatorToken(phone, IdentityConstants.PASSENGER_IDENTITY,TokenConstants.REFRESH_TOKEN_TYPE);
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        //将token存入redis
        stringRedisTemplate.opsForValue().set(RedisPrefixUtils.generatorTokenKey(phone,IdentityConstants.PASSENGER_IDENTITY,TokenConstants.ACCESS_TOKEN_TYPE),accessToken,30,TimeUnit.SECONDS);
        stringRedisTemplate.opsForValue().set(RedisPrefixUtils.generatorTokenKey(phone,IdentityConstants.PASSENGER_IDENTITY,TokenConstants.REFRESH_TOKEN_TYPE),refreshToken,50,TimeUnit.SECONDS);
        return ResponseResult.success(tokenResponse);
    }
}
