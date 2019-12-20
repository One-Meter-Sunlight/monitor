package com.imooc.monitor.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/23 13:16
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public BaseResult handleException(RuntimeException ex) {
        return BaseResult.failure("系统访问异常：" + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResult handleException(Exception ex) {
        return BaseResult.failure(ex.getMessage());
    }
}
