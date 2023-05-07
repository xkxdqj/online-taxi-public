package com.msb.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.msb.internalcommon.dto.TokenResult;

import java.util.HashMap;

/**
 * @Author:maojianfeng
 * @Date:2023-05-03-18:44
 * @Description:
 * @version:1.0
 */
public class JwtUtil {

    public static final String SIGN = "!Q@W#E$R";

    public static final String JWT_KEY_PHONE = "phone";

    public static final String JWT_KEY_IDENTITY = "identity";

    public static final String JWT_KEY_TYP = "typ";

    public static final String JWT_KEY_TIME = "time";

    /**
     * 生成token
     * @param  phone,identity
     * @return
     */
     public static String generatorToken(String phone,String identity,String typ){

         HashMap<String, String> map = new HashMap<>();
         map.put(JWT_KEY_PHONE,phone);
         map.put(JWT_KEY_IDENTITY,identity);
         map.put(JWT_KEY_TYP,typ);

         //获取当前时间戳
         long currentTimeMillis = System.currentTimeMillis();
         map.put(JWT_KEY_TIME,currentTimeMillis+"");

         JWTCreator.Builder builder = JWT.create();

         map.forEach((k,v)->{
            builder.withClaim(k,v);
         });

         return builder.sign(Algorithm.HMAC256(SIGN));
     }

    /**
     * token解析
     */
    public static TokenResult parseToken(String token) {
        HashMap<String, String> map = new HashMap<>();
        JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token).getClaims().forEach((k, v) -> {
            map.put(k, v.asString());
        });
        TokenResult tokenResult = new TokenResult();
        tokenResult.setPhone(map.get(JWT_KEY_PHONE));
        tokenResult.setIdentity(map.get(JWT_KEY_IDENTITY));
        tokenResult.setTyp(map.get(JWT_KEY_TYP));
        return tokenResult;
    }

    /**
     * 校验token,主要检查token是否有异常
     */
    public static TokenResult checkToken(String token) {
        TokenResult tokenResult = null;
        try {
            tokenResult = parseToken(token);
        } catch (Exception e) {
        }
        return tokenResult;
    }
}
