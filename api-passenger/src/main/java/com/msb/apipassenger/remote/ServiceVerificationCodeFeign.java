package com.msb.apipassenger.remote;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author:maojianfeng
 * @Date:2023-04-05-19:26
 * @Description: Feign远程调用VerificationCode服务
 * @version:1.0
 */
@FeignClient("service-verificationcode")
public interface ServiceVerificationCodeFeign {

    @GetMapping("/numberCode/{size}")
    ResponseResult<NumberCodeResponse> getNumberCode(@PathVariable("size") int size);
}
