package com.imooc.monitor.common;

/**
 * 成功/失败枚举
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/12/8 18:29
 */
public enum BaseResultCode {

    SUCCESS(200, "成功"),
    FAILURE(500, "失败");

    private Integer code;
    private String message;

    BaseResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

}
