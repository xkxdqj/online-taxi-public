package com.msb.serviceverificationcode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:maojianfeng
 * @Date:2023-04-02-18:04
 * @Description:com.msb.serviceverificationcode.controller
 * @version:1.0
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "service-virificationcode";
    }
}
