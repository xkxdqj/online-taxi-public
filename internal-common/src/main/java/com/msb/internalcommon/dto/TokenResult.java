package com.msb.internalcommon.dto;

import lombok.Data;

/**
 * @Author:maojianfeng
 * @Date:2023-05-03-23:05
 * @Description:
 * @version:1.0
 */
@Data
public class TokenResult {
    private String phone;
    private String identity;
    private String typ;
}
