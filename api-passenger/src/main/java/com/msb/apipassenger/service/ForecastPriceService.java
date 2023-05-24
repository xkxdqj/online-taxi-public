package com.msb.apipassenger.service;

import com.msb.apipassenger.remote.ServicePriceFeign;
import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.ForecastPriceDTO;
import com.msb.internalcommon.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:maojianfeng
 * @Date:2023-05-23-20:08
 * @Description:
 * @version:1.0
 */
@Service
@Slf4j
public class ForecastPriceService {

    @Autowired
    ServicePriceFeign servicePriceFeign;

    public ResponseResult forecastPrice(ForecastPriceDTO forecastPriceDTO) {
        log.info("出发地经度:{},出发地纬度:{},目的地经度:{},目的地纬度:{}",
                forecastPriceDTO.getOriginLongitude(),
                forecastPriceDTO.getOriginLatitude(),
                forecastPriceDTO.getDestLongitude(),
                forecastPriceDTO.getDestLatitude());

        log.info("调用预估价格服务，估算价格");
        return servicePriceFeign.forecastPrice(forecastPriceDTO);
    }
}
