package com.msb.apipassenger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:maojianfeng
 * @Date:2023-05-05-22:58
 * @Description:
 * @version:1.0
 */
@RestController
public class TestController {

    @GetMapping("authTest")
    public String authTest(){
        return "authTest";
    }

    @GetMapping("noAuthTest")
    public String noAuthTest(){
        return "noAuthTest";
    }
}
