package com.msb.serviceverificationcode;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceVerificationcodeApplicationTests {

    @Test
    void contextLoads() {
        double numberCode = (Math.random()*9+1)*Math.pow(10,5);

        System.out.println((int)numberCode);
    }

}
