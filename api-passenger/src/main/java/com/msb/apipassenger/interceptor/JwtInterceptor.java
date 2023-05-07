package com.msb.apipassenger.interceptor;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson2.JSONObject;
import com.msb.internalcommon.dto.TokenResult;
import com.msb.internalcommon.util.JwtUtil;
import com.msb.internalcommon.util.RedisPrefixUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Author:maojianfeng
 * @Date:2023-05-03-22:34
 * @Description:
 * @version:1.0
 */
public class JwtInterceptor implements HandlerInterceptor {

    private boolean result = true;

    private String resultString;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从header中获取token
        String token = request.getHeader("Authorization");

        //解析token
        TokenResult tokenResult = JwtUtil.checkToken(token);
        //判断token是否为空
        if(tokenResult == null){
            result = false;
            resultString = "accessToken is invalid";
        }else{
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();
            String typ = tokenResult.getTyp();

            //获取redis中的token
            String redisTokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, typ);
            String redisToken = stringRedisTemplate.opsForValue().get(redisTokenKey);

            //判断redis中的token是否为空 或者 与header中的token是否一致
            if(StringUtils.isBlank(redisToken) || !redisToken.equals(token)){
                result = false;
                resultString = "token is invalid";
            }
        }

        if(!result){
            PrintWriter printWriter = response.getWriter();
            printWriter.write(JSONObject.toJSONString(resultString));
        }
        return result;
    }
}
