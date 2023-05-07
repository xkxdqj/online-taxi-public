package com.msb.apipassenger.controller;

import com.msb.apipassenger.service.GenerateValidCodeService;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.VerificationCodeDTO;
import com.msb.internalcommon.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:maojianfeng
 * @Date:2023-02-05-21:43
 * @Description:生成验证码
 * @version:1.0
 */
@RestController
public class VerificationCodeController {

    @Autowired
    private GenerateValidCodeService generateValidCodeService;

    @GetMapping("/generateValidCode")
    public ResponseResult virificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        String phone = verificationCodeDTO.getPhone();

        //调用生成验证码的服务
        ResponseResult res = generateValidCodeService.generateValidCode(verificationCodeDTO);
        //返回结果
        return res;
    }

    @PostMapping("/checkVerificationCode")
    public ResponseResult checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        System.out.println("phone:"+verificationCodeDTO.getPhone());
        System.out.println("verificationCode:"+verificationCodeDTO.getVerificationCode());
        ResponseResult responseResult = generateValidCodeService.checkVerificationCode(verificationCodeDTO);
        return responseResult;
    }
}