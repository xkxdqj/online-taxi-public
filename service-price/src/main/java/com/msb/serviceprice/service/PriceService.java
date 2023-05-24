package com.msb.serviceprice.service;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.ForecastPriceDTO;
import com.msb.internalcommon.response.DirectionResponse;
import com.msb.internalcommon.response.ForecastPriceResponse;
import com.msb.serviceprice.remote.ServiceMapFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:maojianfeng
 * @Date:2023-05-23-21:15
 * @Description:
 * @version:1.0
 */
@Service
@Slf4j
public class PriceService {

    @Autowired
    private ServiceMapFeign serviceMapFeign;

    /**
     * 计算预估价格
     * @param forecastPriceDTO
     * @return
     */
    public ResponseResult forecastPrice(ForecastPriceDTO forecastPriceDTO) {
        log.info("出发地经度:{},出发地纬度:{},目的地经度:{},目的地纬度:{}",
                forecastPriceDTO.getOriginLongitude(),
                forecastPriceDTO.getOriginLatitude(),
                forecastPriceDTO.getDestLongitude(),
                forecastPriceDTO.getDestLatitude());

        log.info("调用地图服务，查询距离和时长");
        ResponseResult<DirectionResponse> direction = serviceMapFeign.driving(forecastPriceDTO);
        Integer distance = direction.getData().getDistance();
        Integer duration = direction.getData().getDuration();
        log.info(("距离："+distance+",时长："+duration));
        log.info("读取计价规则");
        log.info("根据距离、时长和计价规则计算预估价格");
        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(100.00);
        return ResponseResult.success(forecastPriceResponse);
    }
}
