package com.msb.internalcommon.constant;

import lombok.Getter;

/**
 * @Author:maojianfeng
 * @Date:2023-04-02-22:05
 * @Description:验证码响应枚举类
 * @version:1.0
 */

public enum CommonStatusEnum {

    /**
     * Token错误
     */
    TOKEN_ERROR(1199,"token错误"),

    /**
     * 用户不存在
     */
    USER_NOT_EXIST(1200,"用户不存在"),

    SUCCESS(1,"success"),
    FAIL(0,"fail")
    ;

    @Getter
    private int code;
    @Getter
    private String message;

    CommonStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
