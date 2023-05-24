package com.msb.servicepassengeruser.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author:maojianfeng
 * @Date:2023-04-22-22:15
 * @Description:
 * @version:1.0
 */
@Data
@TableName("passenger_user")
public class PassengerUserDTO {
    String passengerPhone;
    String passengerName;
    Byte passengerGender;
    Byte state;
    String createTime;
    String updateTime;
    String profilePhoto;
}
