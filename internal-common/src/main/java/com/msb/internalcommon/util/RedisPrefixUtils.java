package com.msb.internalcommon.util;

/**
 * @Author:maojianfeng
 * @Date:2023-05-03-22:12
 * @Description:
 * @version:1.0
 */
public class RedisPrefixUtils {

    public static final String verificationCodeKey = "VerificationCode-";

    public static final String tokenKey = "Token-";

    /**
     * 获取验证码key
     */
    public static String generatorVerificationKey(String phone){
        return verificationCodeKey + phone;
}

    /**
     * 获取token前缀
     * @param phone
     * @param identity
     * @return
     */
    public static String generatorTokenKey(String phone,String identity,String tokenType){
        return tokenKey +phone+ "-"+identity+"-"+tokenType;
    }
}
