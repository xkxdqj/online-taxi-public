package com.msb.servicemap.remote;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.msb.internalcommon.constant.AmapConfigConstants;
import com.msb.internalcommon.request.ForecastPriceDTO;
import com.msb.internalcommon.response.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author:maojianfeng
 * @Date:2023-05-24-20:13
 * @Description:
 * @version:1.0
 */
@Service
@Slf4j
public class MapDirectionClient {

    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    RestTemplate restTemplate;

    public DirectionResponse direction(ForecastPriceDTO forecastPriceDTO) {
        //路径拼接
        StringBuilder urlBuilber = new StringBuilder();
        urlBuilber.append(AmapConfigConstants.DIRECTION_URL);
        urlBuilber.append("?");
        urlBuilber.append("origin="+forecastPriceDTO.getOriginLongitude()+","+forecastPriceDTO.getOriginLatitude());
        urlBuilber.append("&");
        urlBuilber.append("destination="+forecastPriceDTO.getDestLongitude()+","+forecastPriceDTO.getDestLatitude());
        urlBuilber.append("&");
        urlBuilber.append("extensions=base");
        urlBuilber.append("&");
        urlBuilber.append("output=json");
        urlBuilber.append("&");
        urlBuilber.append("key="+amapKey);

        //调用高德地图接口
        ResponseEntity<String> directionEntity = restTemplate.getForEntity(urlBuilber.toString(), String.class);
        String directionString = directionEntity.getBody();
        // 解析接口
        DirectionResponse directionResponse = parseDirectionEntity(directionString);
        return directionResponse;
    }

    private DirectionResponse parseDirectionEntity(String directionString) {
        DirectionResponse directionResponse = null;
        try {
            // 最外层
            JSONObject result = JSONObject.parseObject(directionString);
            if(result.containsKey(AmapConfigConstants.STATUS)) {
                int status = result.getInteger(AmapConfigConstants.STATUS);
                if (status == 1){
                    if (result.containsKey(AmapConfigConstants.ROUTE)){
                        JSONObject routeObject = result.getJSONObject(AmapConfigConstants.ROUTE);
                        JSONArray pathsArray = routeObject.getJSONArray(AmapConfigConstants.PATHS);
                        JSONObject pathObject = pathsArray.getJSONObject(0);
                        directionResponse = new DirectionResponse();

                        if (pathObject.containsKey(AmapConfigConstants.DISTANCE)){
                            int distance = pathObject.getInteger(AmapConfigConstants.DISTANCE);
                            directionResponse.setDistance(distance);
                        }
                        if (pathObject.containsKey(AmapConfigConstants.DURATION)){
                            int duration = pathObject.getInteger(AmapConfigConstants.DURATION);
                            directionResponse.setDuration(duration);
                        }
                    }
                }
            }

        }catch (Exception e){

        }
        return directionResponse;
    }
}
