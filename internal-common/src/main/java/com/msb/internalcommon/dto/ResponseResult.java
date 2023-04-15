package com.msb.internalcommon.dto;

import com.msb.internalcommon.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author:maojianfeng
 * @Date:2023-04-02-22:28
 * @Description:
 * @version:1.0
 */
@Data
@Accessors(chain = true)
public class ResponseResult <T>{
    private int code;
    private String message;
    private T data;

    /**
     * 统一成功响应方法
     * @return
     */
    public static <T> ResponseResult success(T data){
        return new ResponseResult()
                .setCode(CommonStatusEnum.SUCCESS.getCode())
                .setMessage(CommonStatusEnum.SUCCESS.getMessage())
                .setData(data);
    }

    public static ResponseResult success(){
        return new ResponseResult()
                .setCode(CommonStatusEnum.SUCCESS.getCode())
                .setMessage(CommonStatusEnum.SUCCESS.getMessage());
    }

    /**
     * 默认 失败响应方法
     * @return
     */
    public static ResponseResult fail(){
        return new ResponseResult()
                .setCode(CommonStatusEnum.FAIL.getCode())
                .setMessage(CommonStatusEnum.FAIL.getMessage());
    }

    /**
     * 自定义失败 错误码和提示信息
     * @param code
     * @param message
     * @return
     */
    public static ResponseResult fail(int code,String message){
        return new ResponseResult().setCode(code).setMessage(message);
    }

    /**
     * 自定义 失败响应方法
     * @param code
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult fail(int code,String message,T data){
        return new ResponseResult()
                .setCode(code)
                .setMessage(message)
                .setData(data);
    }
}
