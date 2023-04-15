package com.msb.internalcommon.request;

import lombok.Data;

/**
 * @Author:maojianfeng
 * @Date:2023-02-05-21:46
 * @Description:
 * @version:1.0
 */
@Data
public class VerificationCodeDTO {

    private String phone;
    private String verificationCode;
}
