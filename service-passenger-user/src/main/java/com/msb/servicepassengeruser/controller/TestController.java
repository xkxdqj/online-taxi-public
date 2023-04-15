package com.msb.servicepassengeruser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:maojianfeng
 * @Date:2023-04-09-21:44
 * @Description:
 * @version:1.0
 */
@RestController
public class TestController {

    @GetMapping("/test2")
    public String test(){
        return "service-passenger-user";
    }
}
