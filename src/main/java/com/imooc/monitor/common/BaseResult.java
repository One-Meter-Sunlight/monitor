package com.imooc.monitor.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 公共的返回结果
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/12/4 20:44
 */
@Data
public class BaseResult implements Serializable {

    private Integer code;
    private String message;
    private Object data;

    public BaseResult(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public BaseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult(BaseResultCode resultCode, Object data) {
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.data = data;
    }

    public static BaseResult success(Object data) {
        return new BaseResult(BaseResultCode.SUCCESS, data);
    }

    public static BaseResult failure(Integer code, String message) {
        BaseResult result = new BaseResult(code, message);
        return result;
    }

    public static BaseResult failure(String message) {
        BaseResult result = new BaseResult(BaseResultCode.FAILURE, message);
        return result;
    }

}
