package com.msb.servicepassengeruser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author:maojianfeng
 * @Date:2023-04-22-22:33
 * @Description:
 * @version:1.0
 */
public class Test {

    @org.junit.Test
    public void test(){
        //System.out.println(String.valueOf(System.currentTimeMillis()));

        LocalDateTime now = LocalDateTime.now();
        //使用ofPattern()方法自定义格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = now.format(formatter);
        System.out.println(format);
    }
}
