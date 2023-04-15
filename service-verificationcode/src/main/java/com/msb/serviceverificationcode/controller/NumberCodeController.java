package com.msb.serviceverificationcode.controller;

import com.msb.internalcommon.dto.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.msb.internalcommon.response.NumberCodeResponse;

/**
 * @Author:maojianfeng
 * @Date:2023-04-02-20:22
 * @Description:获取数字验证码
 * @version:1.0
 */
@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size){

        int numberCode = (int)((Math.random() * 9 + 1) * Math.pow(10, size - 1));

        System.out.println("src_number_code:"+numberCode);
        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(numberCode);
        return ResponseResult.success(response);
    }
}
