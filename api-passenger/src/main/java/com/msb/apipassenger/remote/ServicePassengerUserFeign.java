package com.msb.apipassenger.remote;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author:maojianfeng
 * @Date:2023-04-23-10:41
 * @Description: PassengerUser服务远程调用接口
 * @version:1.0
 */
@FeignClient("service-passenger-user")
public interface ServicePassengerUserFeign {

    @RequestMapping(method= RequestMethod.POST,value = "/user")
    ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO);
}
