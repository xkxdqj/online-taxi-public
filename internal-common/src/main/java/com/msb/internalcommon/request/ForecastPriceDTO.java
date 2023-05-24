package com.msb.internalcommon.request;

import lombok.Data;

/**
 * @Author:maojianfeng
 * @Date:2023-05-23-20:02
 * @Description:
 * @version:1.0
 */
@Data
public class ForecastPriceDTO {

    private String originLongitude;

    private String originLatitude;

    private String destLongitude;

    private String destLatitude;
}
