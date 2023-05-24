package com.msb.apipassenger.remote;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.ForecastPriceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author:maojianfeng
 * @Date:2023-05-23-21:39
 * @Description:
 * @version:1.0
 */
@FeignClient("service-price")
public interface ServicePriceFeign {

    @RequestMapping(method = RequestMethod.POST,value = "/forecast-price")
    ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO);
}
