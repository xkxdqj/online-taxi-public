package com.msb.servicemap.service;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.internalcommon.request.ForecastPriceDTO;
import com.msb.internalcommon.response.DirectionResponse;
import com.msb.servicemap.remote.MapDirectionClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author:maojianfeng
 * @Date:2023-05-23-22:39
 * @Description:
 * @version:1.0
 */
@Service
@Slf4j
public class DirectionService {

    @Autowired
    private MapDirectionClient mapDirectionClient;

    public ResponseResult driving(ForecastPriceDTO forecastPriceDTO) {
        DirectionResponse directionResopnse = mapDirectionClient.direction(forecastPriceDTO);
        return ResponseResult.success(directionResopnse);
    }
}
