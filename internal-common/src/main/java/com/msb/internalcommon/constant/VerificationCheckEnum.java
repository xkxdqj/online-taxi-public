package com.msb.internalcommon.constant;

import lombok.Data;
import lombok.Getter;


/**
 * @Author:maojianfeng
 * @Date:2023-05-03-18:03
 * @Description:
 * @version:1.0
 */
public enum VerificationCheckEnum {
    /**
     * 验证码校验状态
     */
    SUCCESS(1,"校验码验证成功"),
    FAIL(0,"校验码验证失败")
    ;

    @Getter
    private int code;
    @Getter
    private String message;

    VerificationCheckEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
