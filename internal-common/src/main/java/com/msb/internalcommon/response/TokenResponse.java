package com.msb.internalcommon.response;

import lombok.Data;

/**
 * @Author:maojianfeng
 * @Date:2023-04-09-15:47
 * @Description:
 * @version:1.0
 */
@Data
public class TokenResponse {

    private String accessToken;

    private String refreshToken;
}
