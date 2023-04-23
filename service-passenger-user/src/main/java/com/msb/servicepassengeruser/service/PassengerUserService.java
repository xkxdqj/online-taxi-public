package com.msb.servicepassengeruser.service;

import com.msb.internalcommon.dto.ResponseResult;
import com.msb.servicepassengeruser.dto.PassengerUserDTO;
import com.msb.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

/**
 * @Author:maojianfeng
 * @Date:2023-04-09-22:29
 * @Description:
 * @version:1.0
 */
@Service
public class PassengerUserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;

    /**
     * 根据手机号查询用户是否已注册
     * @param phone
     * @return
     */
    public ResponseResult loginOrRegister(String phone){

        HashMap<String,Object> map = new HashMap();
        map.put("passenger_phone",phone);

        //根据手机号查询用户
        List<PassengerUserDTO> passengerUserList = passengerUserMapper.selectByMap(map);

        //判断passengerUserList是否为空
        if(passengerUserList.size() == 0){
            //为空，注册用户
            PassengerUserDTO passengerUserDTO = new PassengerUserDTO();
            passengerUserDTO.setPassengerPhone(phone);
            passengerUserDTO.setPassengerName(phone);
            passengerUserDTO.setPassengerGender((byte)1);
            passengerUserDTO.setState((byte)1);
            //设置createTime和updateTime为当前时间，格式为yyyy-MM-dd HH:mm:ss
            LocalDateTime now = LocalDateTime.now();
            //使用ofPattern()方法自定义格式
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatTime = now.format(dateTimeFormatter);

            passengerUserDTO.setCreateTime(formatTime);
            passengerUserDTO.setUpdateTime(formatTime);
            //插入数据
            passengerUserMapper.insert(passengerUserDTO);
        }else{
            //不为空，登录
        }

        return ResponseResult.success();
    }
}
